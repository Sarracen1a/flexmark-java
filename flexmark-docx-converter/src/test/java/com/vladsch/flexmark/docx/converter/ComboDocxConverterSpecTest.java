package com.vladsch.flexmark.docx.converter;

import com.vladsch.flexmark.test.spec.ResourceLocation;
import com.vladsch.flexmark.test.spec.SpecExample;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.List;

public class ComboDocxConverterSpecTest extends ComboDocxConverterSpecTestBase {
    private static final String SPEC_RESOURCE = "/docx_converter_ast_spec.md";
    static final String FILE_TEST_CASE_DUMP_LOCATION = "/flexmark-docx-converter/src/test/resources/docx_converter_ast_spec/";

    public ComboDocxConverterSpecTest(@NotNull SpecExample example) {
        super(example, null);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE);
    }

    @Override
    public @NotNull ResourceLocation getSpecResourceLocation() {
        return ResourceLocation.of(SPEC_RESOURCE);
    }

    @NotNull
    @Override
    public String getFileTestCaseDumpLocation() {
        return FILE_TEST_CASE_DUMP_LOCATION;
    }
}
