//package assignment3.part2;
//
//import java.util.Random;
//
//public class IntelligentSIDC{
//	CleverSIDC<Integer, StudentInfo> adt;
//	private static Random rand=new Random(8636);
//
//	public void SetSIDCThreshold(int size) {//���ݸ�����size�Զ�ѡ��ʹ��AVL��������������
//		if(size>1000) {
//			adt=new AVL<Integer, StudentInfo>();
//		}else {
//			adt=new SortedArray<Integer, StudentInfo>(0,100);
//		}
//	}
//
//	public void setMode(int mode) {
//		if(mode==0) {
//			adt=new AVL<Integer, StudentInfo>();
//		}else {
//			adt=new SortedArray<Integer, StudentInfo>(0,100);
//		}
//	}
//
//	public int size() {
//		return adt.size();
//	}
//
//	public Integer generate() {
//		Integer sidc=0;
//		do {
//			sidc=rand.nextInt(90000000)+10000000;
//		}while(adt.getValues(sidc)!=null);
//		return sidc;
//	}
//	Integer[] allKeys() {
//		Object[] objs=adt.allKeys();
//		Integer[] keys=new Integer[objs.length];
//		for(int i=0;i<keys.length;i++) {
//			keys[i]=(Integer)objs[i];
//		}
//		return keys;
//	}
//	void add(Integer key, StudentInfo value) {
//		adt.add(key, value);
//	}
//	void remove(Integer key) {
//		adt.remove(key);
//	}
//	StudentInfo getValues(Integer key) {
//		return adt.getValues(key);
//	}
//	Integer nextKey(Integer key) {
//		return adt.nextKey(key);
//	}
//	Integer prevKey(Integer key) {
//		return adt.prevKey(key);
//	}
//	int rangeKey(Integer key1,Integer key2) {
//		return adt.rangeKey(key1, key2);
//	}
//}
