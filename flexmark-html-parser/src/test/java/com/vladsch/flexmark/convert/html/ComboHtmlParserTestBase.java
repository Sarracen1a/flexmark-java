package com.vladsch.flexmark.convert.html;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.test.spec.ResourceLocation;
import com.vladsch.flexmark.test.spec.SpecExample;
import com.vladsch.flexmark.test.spec.SpecReader;
import com.vladsch.flexmark.test.util.ComboSpecTestCase;
import com.vladsch.flexmark.test.util.FlexmarkSpecExampleRenderer;
import com.vladsch.flexmark.test.util.SpecExampleRenderer;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboHtmlParserTestBase extends ComboSpecTestCase {
    private static final String SPEC_RESOURCE = "/html_parser_issue_spec.md";
    @SuppressWarnings("deprecation") private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            .set(FlexmarkHtmlParser.OUTPUT_ATTRIBUTES_ID, false)
            ;

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("output-unknown", new MutableDataSet().set(FlexmarkHtmlParser.OUTPUT_UNKNOWN_TAGS, true));
        optionsMap.put("nbsp", new MutableDataSet().set(FlexmarkHtmlParser.NBSP_TEXT, "&nbsp;"));
        optionsMap.put("no-quotes", new MutableDataSet().set(FlexmarkHtmlParser.TYPOGRAPHIC_QUOTES, false));
        optionsMap.put("no-smarts", new MutableDataSet().set(FlexmarkHtmlParser.TYPOGRAPHIC_SMARTS, false));
        optionsMap.put("wrap-autolinks", new MutableDataSet().set(FlexmarkHtmlParser.WRAP_AUTO_LINKS, true));
        optionsMap.put("no-autolinks", new MutableDataSet().set(FlexmarkHtmlParser.EXTRACT_AUTO_LINKS, false));
        optionsMap.put("list-break", new MutableDataSet().set(FlexmarkHtmlParser.LISTS_END_ON_DOUBLE_BLANK, true));
        optionsMap.put("src-pos", new MutableDataSet().set(HtmlRenderer.SOURCE_POSITION_ATTRIBUTE, "md-pos"));
        optionsMap.put("code-emphasis", new MutableDataSet().set(FlexmarkHtmlParser.PRE_CODE_PRESERVE_EMPHASIS, true));
        optionsMap.put("skip-inline-strong", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_INLINE_STRONG, true));
        optionsMap.put("skip-inline-emphasis", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_INLINE_EMPHASIS, true));
        optionsMap.put("skip-inline-code", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_INLINE_CODE, true));
        optionsMap.put("skip-inline-del", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_INLINE_DEL, true));
        optionsMap.put("skip-inline-ins", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_INLINE_INS, true));
        optionsMap.put("skip-inline-sub", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_INLINE_SUB, true));
        optionsMap.put("skip-inline-sup", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_INLINE_SUP, true));
        optionsMap.put("skip-heading-1", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_HEADING_1, true));
        optionsMap.put("skip-heading-2", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_HEADING_2, true));
        optionsMap.put("skip-heading-3", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_HEADING_3, true));
        optionsMap.put("skip-heading-4", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_HEADING_4, true));
        optionsMap.put("skip-heading-5", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_HEADING_5, true));
        optionsMap.put("skip-heading-6", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_HEADING_6, true));
        optionsMap.put("skip-attributes", new MutableDataSet().set(FlexmarkHtmlParser.SKIP_ATTRIBUTES, true));
    }
    public ComboHtmlParserTestBase(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE);
    }

    @Nullable
    @Override
    public DataHolder options(String option) {
        return optionsMap.get(option);
    }

    @Override
    public @NotNull ResourceLocation getSpecResourceLocation() {
        return ResourceLocation.of(SPEC_RESOURCE);
    }

    @Override
    public @NotNull SpecExampleRenderer getSpecExampleRenderer(@NotNull SpecExample example, @Nullable DataHolder exampleOptions) {
        DataHolder combineOptions = combineOptions(OPTIONS, exampleOptions);
        return new FlexmarkSpecExampleRenderer(example, combineOptions, new HtmlParser(combineOptions), new HtmlRootNodeRenderer(combineOptions), true);
    }

    @NotNull
    @Override
    public HtmlSpecReader create(@NotNull ResourceLocation location) {
        return SpecReader.create(location, (stream, fileUrl) -> new HtmlSpecReader(stream, this, fileUrl));
    }

    @Override
    protected void assertRendering(@Nullable String fileUrl, @NotNull String source, @NotNull String expectedHtml, @Nullable String expectedAst, @Nullable String optionsSet) {
        // reverse source and html
        super.assertRendering(fileUrl, expectedHtml, source, null, optionsSet);
    }
}