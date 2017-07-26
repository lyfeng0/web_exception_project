package com.drools.test;

import java.util.Iterator;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
/**
 * 测试规则的代码
 * @author sxf
 *
 */
public class DroolsTester {

     public static void main(String[] args) throws Exception {
          KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
          System.out.println("kbuilder："+kbuilder);
          kbuilder.add(ResourceFactory.newClassPathResource("test.drl"), ResourceType.DRL);
          if (kbuilder.hasErrors()) {
           System.out.println("规则错误：");
           Iterator<KnowledgeBuilderError> it = kbuilder.getErrors().iterator();
           while (it.hasNext()) System.out.println(it.next());
           return;
          }
          KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
          kb.addKnowledgePackages(kbuilder.getKnowledgePackages());
          StatefulKnowledgeSession s = kb.newStatefulKnowledgeSession();
          User user=new User();
          user.setMoney(50);
          s.insert(user);
          s.fireAllRules();
          s.dispose();
         }
         
}
