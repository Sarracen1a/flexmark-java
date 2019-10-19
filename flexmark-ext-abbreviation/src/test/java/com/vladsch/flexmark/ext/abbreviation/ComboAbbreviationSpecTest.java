package com.vladsch.flexmark.ext.abbreviation;

import com.vladsch.flexmark.ext.escaped.character.EscapedCharacterExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.core.test.util.RendererSpecTest;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.superscript.SuperscriptExtension;
import com.vladsch.flexmark.test.spec.ResourceLocation;
import com.vladsch.flexmark.test.spec.SpecExample;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.*;

public class ComboAbbreviationSpecTest extends RendererSpecTest {
    static final String SPEC_RESOURCE = "/ext_abbreviation_ast_spec.md";
    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(Parser.EXTENSIONS, Arrays.asList(
                    EscapedCharacterExtension.create(),
                    AbbreviationExtension.create(),
                    TypographicExtension.create(),
                    InsExtension.create(),
                    StrikethroughSubscriptExtension.create(),
                    SuperscriptExtension.create())
            )
            .toImmutable();

    private static final Map<String, DataHolder> optionsMap = placementAndSortOptions(AbbreviationExtension.ABBREVIATIONS_PLACEMENT, AbbreviationExtension.ABBREVIATIONS_SORT);
    static {
        optionsMap.put("links", new MutableDataSet().set(AbbreviationExtension.USE_LINKS, true));
        optionsMap.put("no-abbr", new MutableDataSet().set(UNLOAD_EXTENSIONS, Collections.singletonList(AbbreviationExtension.class)));
    }

    public ComboAbbreviationSpecTest(@NotNull SpecExample example) {
        super(example, optionsMap, OPTIONS);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE);
    }

    @Override
    public @NotNull ResourceLocation getSpecResourceLocation() {
        return ResourceLocation.of(SPEC_RESOURCE);
    }
}
