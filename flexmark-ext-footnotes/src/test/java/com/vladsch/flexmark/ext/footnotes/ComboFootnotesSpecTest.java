package com.vladsch.flexmark.ext.footnotes;

import com.vladsch.flexmark.core.test.util.RendererSpecTest;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.test.spec.ResourceLocation;
import com.vladsch.flexmark.test.spec.SpecExample;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboFootnotesSpecTest extends RendererSpecTest {
    static final String SPEC_RESOURCE = "/ext_footnotes_ast_spec.md";
    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(Parser.EXTENSIONS, Collections.singleton(FootnoteExtension.create()))
            .toImmutable();

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("custom", new MutableDataSet()
                .set(FootnoteExtension.FOOTNOTE_REF_PREFIX, "[")
                .set(FootnoteExtension.FOOTNOTE_REF_SUFFIX, "]")
                .set(FootnoteExtension.FOOTNOTE_BACK_REF_STRING, "&lt;back&gt;")
        );
        optionsMap.put("link-class-none", new MutableDataSet().set(FootnoteExtension.FOOTNOTE_LINK_REF_CLASS, ""));
        optionsMap.put("link-class-text", new MutableDataSet().set(FootnoteExtension.FOOTNOTE_LINK_REF_CLASS, "text"));
        optionsMap.put("back-link-class-none", new MutableDataSet().set(FootnoteExtension.FOOTNOTE_BACK_LINK_REF_CLASS, ""));
        optionsMap.put("back-link-class-text", new MutableDataSet().set(FootnoteExtension.FOOTNOTE_BACK_LINK_REF_CLASS, "text"));
        optionsMap.put("item-indent-8", new MutableDataSet().set(Parser.LISTS_ITEM_INDENT, 8));
    }
    public ComboFootnotesSpecTest(@NotNull SpecExample example) {
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
