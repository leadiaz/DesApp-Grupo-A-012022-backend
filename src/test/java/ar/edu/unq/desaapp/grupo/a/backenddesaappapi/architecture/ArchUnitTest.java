package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "ar.edu.unq.desaapp.grupo.a.backenddesaappapi", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchUnitTest {
    @ArchTest
    public static final ArchRule layerRule =
            layeredArchitecture()
                    .layer("Controller").definedBy("..webservices..")
                    .layer("Service").definedBy("..services..")
                    .layer("Persistence").definedBy("..repositories..")
                    .layer("Security").definedBy("..security..")
                    .layer("Config").definedBy("..config..")
                    .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Security", "Config")
                    .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");

    @ArchTest
    public static final ArchRule controllerNameRule =
            classes().that().haveSimpleNameEndingWith("Controller")
                    .should().resideInAPackage("..webservices..");
    @ArchTest
    public static final ArchRule serviceNameRule =
            classes().that().haveSimpleNameEndingWith("Service")
                    .should().resideInAPackage("..services..");
    @ArchTest
    public static final ArchRule repositoryNameRule =
            classes().that().haveSimpleNameEndingWith("Repository")
                    .should().resideInAPackage("..repositories..");
}
