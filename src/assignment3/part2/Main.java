package assignment3.part2;

//package assignment3.part2;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Random;
//
//public class Main {
//	static Random rand=new Random(123);
//
//	public static void test(String filePath,int mode) {
//		try {
//			long begin=System.nanoTime();
//			IntelligentSIDC db=new IntelligentSIDC();
//			db.setMode(mode);
//			BufferedReader br=new BufferedReader(new FileReader(filePath));
//			String line;
//			StudentInfo stu;
//			int[] removeAfter=new int[50];
//			int counter=0;
//			while((line=br.readLine())!=null) {
//				stu=new StudentInfo(Integer.valueOf(line));
//				if(counter<50) {
//					removeAfter[counter++]=stu.sidc();
//				}
//				db.add(stu.sidc(), stu);
//			}
//			br.close();
//			System.out.println("add num:"+db.size());
//			for(int i=0;i<removeAfter.length;i++) {
//				db.remove(removeAfter[i]);
//			}
//			System.out.println("remove num:"+removeAfter.length);
//			System.out.println("total num:"+db.size());
//			double elapsed=(System.nanoTime()-begin)*1.0/1000000.0;
//			System.out.println("elapsed time:"+elapsed);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void test(String filePath) {
//		System.out.println("Test file path:"+filePath);
//		System.out.println("AVL tree test:");
//		test(filePath,0);
//		System.out.println("Sorted array test:");
//		test(filePath,1);
//	}
//
//	public static void main(String[] args) {
//		test("CSTA_test_files/CSTA_test_file1.txt");
//		return;
//	}
//
//}
public class Main {
	private static final String val = "val";
	public static void main(String[] args) {
		CleverSIDC cleverSIDC = new CleverSIDCImpl();
		cleverSIDC.setSIDCThreshold(1000);
		cleverSIDC.add(100, val);
		cleverSIDC.add(2330, val);
		cleverSIDC.add(30, val);
		cleverSIDC.add(1, val);
		cleverSIDC.add(33210, val);
		cleverSIDC.add(10, val);
		cleverSIDC.add(8, val);
		cleverSIDC.add(1000, val);
		for (int key: cleverSIDC.allKeys()) {
			System.out.print(" " + key);
		}
		System.out.println();

		// test add
		cleverSIDC.add(0, val);
		cleverSIDC.add(33333333, val);
		cleverSIDC.add(2, val);
		for (int key: cleverSIDC.allKeys()) {
			System.out.print(" " + key);
		}
		System.out.println();

		// test remove
		cleverSIDC.remove(0);
		cleverSIDC.remove(33333333);
		cleverSIDC.remove(2);
		for (int key: cleverSIDC.allKeys()) {
			System.out.print(" " + key);
		}
		System.out.println();

		// test generate
		System.out.println("\ntest key\n");
		int key = -1;
		for (int i = 0; i < 100; i++) {
			key = cleverSIDC.generate();
			if (key >= 100000000 || cleverSIDC.getValues(key) != null) {
				System.out.println("key: " + key + " is invalid");
			}
		}

		System.out.println(cleverSIDC.generate());
		System.out.println(cleverSIDC.generate());
		System.out.println(cleverSIDC.generate());

		// test nextKey
		System.out.println(cleverSIDC.nextKey(1));
		System.out.println(cleverSIDC.nextKey(2330));

		// test prevKey
		System.out.println(cleverSIDC.prevKey(8));
		System.out.println(cleverSIDC.prevKey(33210));

		// test rangeKey
		System.out.println(cleverSIDC.rangeKey(1, 33210));
		System.out.println(cleverSIDC.rangeKey(0, 9999999));
		System.out.println(cleverSIDC.rangeKey(1, 1));
		System.out.println(cleverSIDC.rangeKey(0, 7));
	}
}