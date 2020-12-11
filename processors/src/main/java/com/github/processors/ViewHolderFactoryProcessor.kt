package com.github.processors

import com.github.annotations.ViewHolder
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import me.eugeniomarletti.kotlin.metadata.KotlinMetadataUtils
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import java.io.File
import java.lang.StringBuilder
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class ViewHolderFactoryProcessor() : KotlinAbstractProcessor(), KotlinMetadataUtils {

    override fun getSupportedAnnotationTypes() = setOf(ANNOTATION.canonicalName)

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val outputDir = generatedDir
        if (outputDir == null) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Cannot find generated output dir.")
            return false
        }

        val viewHolderElements = roundEnv.getElementsAnnotatedWith(ANNOTATION)

        if (viewHolderElements.isNotEmpty())
            generateCode(viewHolderElements.map { it as TypeElement }, outputDir)


        return true
    }

    private fun generateCode(elements: List<TypeElement>, outputDir: File) {

        val whenStatement: StringBuilder = StringBuilder()
            .append("val inflater = LayoutInflater.from(viewGroup.context)")
            .append("\n")
            .append("return when(viewType) {")
            .append("\n")

        messager.printMessage(Diagnostic.Kind.WARNING, elements.toString())


        elements.forEach { element ->

            val layout = element.getAnnotation(ANNOTATION).layout
            val viewType = element.getAnnotation(ANNOTATION).viewType
            val viewHolderClassName = element.asClassName().simpleName
            val viewHolderPackageName = element.asClassName().packageName

            val viewHolder = ClassName(viewHolderPackageName, viewHolderClassName)

            whenStatement
                .append("   $viewType-> {")
                .append("       $viewHolder(inflater.inflate(R.layout.$layout, viewGroup, false))")
                .append("\n")
                .append("   }")
                .append("\n")

        }

        whenStatement
            .append("   else -> throw(Exception(\"should never be here\"))")
            .append("\n")
            .append("} as T")

        val packageName = "com.github.nytimesnewsapp"
        val generatedClass = "ViewHolderFactory"
        val viewGroup = ClassName("android.view", "ViewGroup")

        val companion = FunSpec.builder("create")
            .addModifiers(KModifier.INLINE)
            .addParameter(ParameterSpec.builder("viewType", Int::class).build())
            .addTypeVariable(
                TypeVariableName.Companion.invoke(
                    "reified I : BaseUIModel, reified T : BaseViewHolder<I>"
                )
            )
            .addParameter("viewGroup", viewGroup)
            .returns(TypeVariableName.invoke("T"))
            .addStatement(whenStatement.toString())
            .build()

        val factoryTypeSpec = TypeSpec.objectBuilder(ClassName(packageName, generatedClass))
            .addFunction(companion)
            .build()

        FileSpec.builder(packageName, generatedClass)
            .addType(factoryTypeSpec)
            .addImport(
                ClassName(packageName, "base", "presentation", "model"), "BaseUIModel"
            )
            .addImport(
                ClassName(packageName, "base", "presentation"), "BaseViewHolder"
            )
            .addImport(
                ClassName("", packageName), "R"
            )
            .addImport(ClassName("android", "view"), "LayoutInflater")
            .build().writeTo(outputDir)
    }

    companion object {
        val ANNOTATION = ViewHolder::class.java
    }
}