package Dynamic_Query;

import java.util.ArrayList;

public class Duplicate_query_dyn {

	public String duplicate_test(String tab,String col,String sch)
	{
		
		String dup_query="select t.cnt from (select "+col+",count(*) as cnt from "+sch+"."+tab+" group by "+col+" having count(*)>1) t";
		return dup_query;
				
		//select t.cnt from (select empno,count(*) as cnt from emp having count(*)>1) t
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Duplicate_query_dyn d=new  Duplicate_query_dyn();
		ArrayList<String> list_DQ = new ArrayList<String>();
       String temp="CREATE_DTIME,LAST_UPDATE_DTIME,EXPIRY_DTIME";
       String table="VENDOR_ENTITY_LIST";
       String sch="FINMASTER";
       ArrayList<String> list_dup = new ArrayList<String>();
		String[] list_dup_1 = temp.split(",");
		for (String list_dup_tmp : list_dup_1) {
			// System.out.println(list_agg_2);
			// select sum(nvl(comm,0)) from emp
			list_dup.add(list_dup_tmp);
			//list_agg.add("avg" + "(nvl(" + list_agg_2 + ",0))");
			// list_agg.add("sum" + "(" + list_agg_2 + ")");
			// list_agg.add("avg" + "(" + list_agg_2 + ")");
		}
		//select empno,count(*) from emp having count(*)>1
		//System.out.println(list_dup);
		for(int i=0;i<list_dup.size();i++)
		{
			System.out.println(list_dup.get(i));
			String temp_q=d.duplicate_test(table, list_dup.get(i),sch);
			list_DQ.add(temp_q);
		}
		System.out.println(list_DQ);
	}

}
