package package1;

public class Quickx2D {

	public Quickx2D() {
	}

	public static double[][] QuickSort(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			// System.out.println("A yý basýyom "+a[i]);

		}

		int l = 0;
		int r = a.length - 1;
		QuickSort(a, l, r);
		// assert isSorted(a);
		 return a;
	}

	private static void QuickSort(double[][] a, int l, int r) {
		int i;
		if (r > l) {
			i = partition(a, l, r);
			QuickSort(a, l, i - 1);
			QuickSort(a, i + 1, r);
		}
	}

	private static int partition(double[][] a, int l, int h) {
		// pivot (Element to be placed at right position)
		double pivot = a[h][0];

		int i = (l - 1); // Index of smaller element
		double[] tempA;

		for (int j = l; j <= h - 1; j++) {
			// If current element is smaller than the pivot

			if (a[j][0] < pivot) {
				i++; // increment index of smaller element
				tempA = a[i];

				// System.out.println("aj bakcaz"+a[j]);

				a[i] = a[j];
				a[j] = tempA;

				// System.out.println(tempA+"->"+a[i]);
			}
		}
		tempA = a[i + 1];

		a[i + 1] = a[h];
		a[h] = tempA;

		// System.out.println(tempA+"->"+a[i+1]);
		return (i + 1);
	}

	private static boolean isSorted(double[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] < a[i - 1])
				return false;
		return true;
	}

}
