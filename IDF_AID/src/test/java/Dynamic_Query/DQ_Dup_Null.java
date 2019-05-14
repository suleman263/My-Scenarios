package Dynamic_Query;

import java.util.ArrayList;

public class DQ_Dup_Null {
	public String null_query(String tab,String col,String sch)
	{
		
		String dup_query="select count(*) from "+tab+" where "+col+" is null";
		return dup_query;
				
		//select count(*) from emp where comm is null
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DQ_Dup_Null d=new  DQ_Dup_Null();
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
			String temp_q=d.null_query(table, list_dup.get(i),sch);
			list_DQ.add(temp_q);
		}
		System.out.println(list_DQ);
	}
	

}
