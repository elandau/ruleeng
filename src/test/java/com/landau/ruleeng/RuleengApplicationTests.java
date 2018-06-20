package com.landau.ruleeng;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.landau.ruleeng.parser.RuleEngLexer;
import com.landau.ruleeng.parser.RuleEngParser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleengApplicationTests {

	@Test
	public void contextLoads() {
	    String expr = "(country == US) && {{(deviceName == PS4) && (appVersion >= 102)} || {(deviceName == ANDROID) || (appVersion >= 42)}}";
	    
	    RuleEngLexer lexer = new RuleEngLexer(new ANTLRInputStream(expr));
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
        RuleEngParser parser = new RuleEngParser(tokens);
        RuleContext context = parser.parse().getRuleContext();
        explore(context, 2);
	}
	
	private void explore(RuleContext ctx, int indentation) {
        String ruleName = RuleEngParser.ruleNames[ctx.getRuleIndex()];
        for (int i=0;i<indentation;i++) {
            System.out.print("  ");
        }
        System.out.println(ruleName + " " + ctx.getClass().getSimpleName() + " " + ctx.getText());
        for (int i=0;i<ctx.getChildCount();i++) {
            ParseTree element = ctx.getChild(i);
            if (element instanceof RuleContext) {
                explore((RuleContext)element, indentation + 1);
            }
        }
    }
}
