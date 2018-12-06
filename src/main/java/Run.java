import java.io.File;

public class Run {

	private static String PATH = "";
	private static String SUFFIX = "";


	public static void main(String[] args) {
		PATH = args[0];
		SUFFIX = args[1];
		System.out.println("The path you just input is : " + PATH);
		if (PATH == null || PATH.length() == 0) {
			System.out.println("The path is not correct");
		}
		int[] run = run(PATH);
		if (null == null) {
			return;
		}
		System.out.println("total files is " + run[0] + "; total directories is " + run[1] + ";delete files is : " + run[2]);
	}


	public static int[] run(String path) {
		int[] statistic = new int[3];
		// 0 total files
		// 1 total directories
		// 2 total deleted files

		File file = new File(path);
		if (!file.exists()) {
			System.out.println("path is not exists please check again");
			return null;
		}
		File[] files = file.listFiles();
		if (files.length == 0) {
			System.out.println("empty path : " + file.getPath());
			return null;
		}
		for (File sub : files) {
			if (!sub.exists()) {
				continue;
			}
			if (sub.isDirectory()) {
				try {
					statistic = add(run(sub.getPath()), statistic);
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("something bad has happened");
				}
				statistic[1]++;
			} else {
				statistic[0]++;
				if (sub.getName().endsWith(SUFFIX)) {
					System.out.println("delete file : " + sub.getName());
					sub.delete();
					statistic[2]++;
				}
			}
		}
		return statistic;
	}

	private static int[] add(int[] a, int[] b) {
		if (a == null) {
			return b;
		}
		for (int i = 0; i < b.length; i++) {
			b[i] = b[i] + a[i];
		}
		return b;
	}

}
