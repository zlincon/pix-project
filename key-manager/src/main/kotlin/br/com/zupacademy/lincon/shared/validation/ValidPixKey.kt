package br.com.zupacademy.lincon.pix.registra

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.TYPE
import kotlin.reflect.KClass

@MustBeDocumented
@Target(CLASS, TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidPixKeyValidator::class])
annotation class ValidPixKey(
    val message: String = "Chave pix inválida (\${validateValue.tipo})",
    val group: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
class ValidPixKeyValidator : ConstraintValidator<ValidPixKey, NovaChavePix> {
    override fun isValid(
        value: NovaChavePix?,
        annotationMetadata: AnnotationValue<ValidPixKey>,
        context: ConstraintValidatorContext
    ): Boolean {
        println("Anotation isVadid: "+value?.chave)
        println("Anotation isVadid: "+value?.tipo?.valida(value.chave))
        if (value?.tipo == null) {
            return false
        }
        return value.tipo.valida(value.chave)
    }

}
