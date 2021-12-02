package org.mybatis.generator.internal;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 *
 * @desc 自定义类注释以及属性注释
 * @author bosong
 * @date 2019/6/13 9:51
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        String remark = introspectedTable.getRemarks();
        if (remark != null && !("").equals(remark)) {
            topLevelClass.addJavaDocLine(" * " + remark);
        }
        topLevelClass.addJavaDocLine(" * ");
        topLevelClass.addJavaDocLine(" * " + "@desc ");
        topLevelClass.addJavaDocLine(" * " + "@author bosong");
        topLevelClass.addJavaDocLine(" * " + "@date " + getDateContent());
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remark = introspectedColumn.getRemarks();
        if (remark != null && !("".equals(remark))) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + remark);
            field.addJavaDocLine(" */");
        }
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }
}
