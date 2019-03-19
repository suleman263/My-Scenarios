package Dynamic_Query;

import java.util.ArrayList;

public class dynamic_query_test {
public static String space_1=" ";
public static String underscroe="_";
public static String where_1="where";
public static String dot=".";
public static String max_min_col="PERIOD_END_DATE,VALUE_TEXT";
public static String table1="FACT_DATA_POINT";
public static String schema="FINMASTER";
public static String where="DATA_POINT_ID = '1039809'";
public static String where_2=null;
public static String max_field = "Null";
public static String agg_field="value_text,value_text_1,value_text,value_text_1,value_text,value_text_1";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String table1="FACT_DATA_POINT";
String schema="FINMASTER";
String where="DATA_POINT_ID = '1039809'";
String max_field = "Null";
String agg_field_1 = "Null";
//count(*)
String count_1 = "select"+space_1+"count(*)"+space_1+"from"+space_1+schema+dot+table1+space_1+space_1+where_1+ space_1+where;
System.out.println(count_1);
ArrayList<String> list=new ArrayList<String>();
String[] max_min_col_1=max_min_col.split(",");
for(String max_min_col_2 : max_min_col_1){
//System.out.println(max_min_col_2);
list.add("max"+"("+max_min_col_2+")");
list.add("min"+"("+max_min_col_2+")");

}
for(int i=0;i<list.size();i++)
{
	max_field=max_field + ","+list.get(i);
	
}
//System.out.println(max_field.replace("Null,", ""));
String max_c=max_field.replace("Null,", "");
String max_query = "select"+space_1+max_c+space_1+"from"+space_1+schema+dot+table1+space_1+space_1+where_1+ space_1+where_2;
String max_query_where_null = "select"+space_1+max_c+space_1+"from"+space_1+schema+dot+table1+space_1;
//System.out.println(count_1);
//System.out.println(max_query);
System.out.println(max_query_where_null);

//Aggregation
ArrayList<String> list_agg=new ArrayList<String>();
String[] list_agg_1=agg_field.split(",");
for(String list_agg_2 : list_agg_1){
//System.out.println(list_agg_2);
list_agg.add("sum"+"("+list_agg_2+")");
list_agg.add("agg"+"("+list_agg_2+")");
}
for(int i=0;i<list_agg.size();i++)
{
	agg_field_1=agg_field_1 + ","+list_agg.get(i);
	
}
//System.out.println(agg_field_1.replace("Null,", ""));
String agg_3=agg_field_1.replace("Null,", "");
//System.out.println(agg_3);
String agg_query="select" + space_1+ agg_3+space_1+"from"+space_1+schema+dot+table1;
System.out.println(agg_query);
}



	}
	

