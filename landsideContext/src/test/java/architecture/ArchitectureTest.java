package architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "be.kdg.prog6", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {
    private static final String DOMAIN_LAYER = "be.kdg.prog6.domain..";
    private static final String ADAPTER_LAYER = "be.kdg.prog6.adapter..";
    private static final String CORE_LAYER = "be.kdg.prog6.core..";
    private static final String PORT_LAYER = "be.kdg.prog6.port..";

    private static final String PORT_OUT_LAYER = "be.kdg.prog6.port.out..";

    @ArchTest
    static final ArchRule domainShouldNotDependOnAnyOtherLayerRule =
            noClasses().that().resideInAPackage(DOMAIN_LAYER)
                    .should().dependOnClassesThat().resideInAnyPackage(
                            ADAPTER_LAYER,
                            PORT_LAYER,
                            CORE_LAYER
                    )
                    .because("This conflicts with hexagonal architecture: Domain should not depend on other layers.");
    @ArchTest
    static final ArchRule portLayerClassesShouldEndWithPort =
            ArchRuleDefinition.classes()
                    .that().resideInAPackage(PORT_OUT_LAYER)
                    .should().haveSimpleNameEndingWith("Port")
                    .because("All classes in the PORT_LAYER should end with 'Port'");

}
