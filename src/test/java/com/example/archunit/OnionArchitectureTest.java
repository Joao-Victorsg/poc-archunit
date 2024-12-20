package com.example.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.example.archunit")
class OnionArchitectureTest {

    @ArchTest
    public static final ArchRule adaptersSoPodemDependerDeClassesForaDoPacoteAdapter =
            classes().that()
                    .resideInAPackage("..adapter..")
                    .should()
                    .dependOnClassesThat()
                    .resideOutsideOfPackage("..adapter..");

    @ArchTest
    public static final ArchRule coreNaoPodeDependerDePacotesForaDoCore =
            classes().that()
                    .resideInAPackage("..core..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("..core..", "..java..");

    @ArchTest
    public static final ArchRule coreNaoPodeDependerDoAdapter =
            noClasses().that().resideInAPackage("..core..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..adapter..");

    @ArchTest
    public static final ArchRule useCaseNaoPodeChamarOutroUseCase =
            noClasses().that()
                    .resideInAPackage("..usecase..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..usecase..");

    @ArchTest
    public static final ArchRule classesQueEstaoNoPacoteUseCaseDevemTerminarNomeComUseCase =
            classes().that()
                    .resideInAPackage("..usecase..")
                    .should()
                    .haveSimpleNameEndingWith("UseCase");

    @ArchTest
    public static final ArchRule classesQueTerminamComPortDevemEstarDentroDoPacoteCore =
            classes().that()
                    .haveSimpleNameEndingWith("Port")
                    .should()
                    .resideInAPackage("..core..");

    @ArchTest
    public static final ArchRule classesQueEstaoNoPacotePortDevemTerminarNomeComPort =
            classes().that()
                    .resideInAPackage("..port..")
                    .should()
                    .haveSimpleNameEndingWith("Port");

    @ArchTest
    public static final ArchRule pacoteEntityDeveEstarDentroDoPacoteAdapter =
            classes().that()
                    .resideInAPackage("..entity..")
                    .should()
                    .resideInAPackage("..adapter..entity");


    @ArchTest
    public static final ArchRule entidadesSoPodemDependerDeOutrasEntidades =
            classes().that()
                    .resideInAPackage("..entity..")
                    .should()
                    .onlyDependOnClassesThat()
                    .resideInAnyPackage("..entity..", "..java..");

    @ArchTest
    public static final ArchRule classesQueEstaoNoPacoteEntityDevemTerminarComEntity =
            classes().that()
                    .resideInAPackage("..entity..")
                    .should()
                    .haveSimpleNameEndingWith("Entity");
}