package com.exercise.cleanarch

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AnalyzeClasses(packagesOf = [HexagonalWithOnionCoreArchTest::class])
class HexagonalWithOnionCoreArchTest {

    companion object {
        private val BASE_PACKAGE = HexagonalWithOnionCoreArchTest::class.java.`package`.name

        private val CORE_PACKAGE = "$BASE_PACKAGE.core"
        private val CORE_ENTITY_PACKAGE = "$CORE_PACKAGE.entity"
        private val CORE_SERVICE_PACKAGE = "$CORE_PACKAGE.service"

        private val PORTS_PACKAGE = "$BASE_PACKAGE.ports"

        private val INFRA_PACKAGE = "$BASE_PACKAGE.infra"
    }

    @ArchTest
    val packagesShouldBeFreeOfCycles =
        SlicesRuleDefinition.slices()
            .matching("$BASE_PACKAGE.(**)..")
            .should()
            .beFreeOfCycles()!!

    @ArchTest
    val coreEntityDoesNotHaveOutgoingDependencies =
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("$CORE_ENTITY_PACKAGE..")
            .should()
            .dependOnClassesThat()
            .resideOutsideOfPackages(
                "$CORE_ENTITY_PACKAGE..", "java..", "javax.persistence..", "org.jetbrains..", "kotlin.."
            )!!

    @ArchTest
    val coreLayerDoesNotDependOnTheInfraLayer =
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("$CORE_PACKAGE..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("$INFRA_PACKAGE..")!!

    @ArchTest
    val infraLayerDoesNotDependOnTheServiceLayer =
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("$INFRA_PACKAGE..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("$CORE_SERVICE_PACKAGE..")!!

    @ArchTest
    val portsLayerShouldOnlyContainInterfaces =
        ArchRuleDefinition.classes()
            .that()
            .resideInAPackage("$PORTS_PACKAGE..")
            .should()
            .beInterfaces()!!

}