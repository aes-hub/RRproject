package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class KNN extends JFrame implements ActionListener{

	private double[][] features = null;
	public static double candidate[] = new double[4];
	private static int fileLength;
	Scanner sc = new Scanner(System.in);
	int knn_value = 1;
	
	private JTextField txtAge,txtGpa,txtSocialSkill,txtAlgorithmSkill,txtKey;
	private JButton btnresult;
	
	
	public KNN(){
		gui();
		setLayout(null);
		setTitle("Hire");
		setSize(500,500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void gui() {
		txtKey = new JTextField("What will be the K");
		txtKey.setLocation(10,10); //Size label initialized
		txtKey.setSize(150,30);
		add(txtKey);
		
		txtAge = new JTextField("Enter the Age");
		txtAge.setLocation(10,60); //Size label initialized
		txtAge.setSize(150,30);
		add(txtAge);
		
		txtGpa = new JTextField("Enter the Gpa");
		txtGpa.setLocation(10,110); //Size label initialized
		txtGpa.setSize(150,30);
		add(txtGpa);
		
		txtSocialSkill = new JTextField("Enter the Social Skill");
		txtSocialSkill.setLocation(10,160); //Size label initialized
		txtSocialSkill.setSize(150,30);
		add(txtSocialSkill);
		
		txtAlgorithmSkill = new JTextField("Enter the Algorithm Skill");
		txtAlgorithmSkill.setLocation(10,210); //Size label initialized
		txtAlgorithmSkill.setSize(150,30);
		add(txtAlgorithmSkill);
		
		btnresult = new JButton("Result");
		btnresult.setLocation(10,260); //Size label initialized
		btnresult.setSize(75,30);
		add(btnresult);
		
		btnresult.addActionListener(this);
	}
	
	
	
	
	
	public double[][] nearestNeighbour(double[][] a) {
		double[][] nearest = new double[knn_value][2];
		for (int i = 0; i < knn_value; i++) {
			nearest[i][0] = a[i][0];
			nearest[i][1] = a[i][1];
		}
		return nearest;
	}

	public int count(double[][] result) {
		double ones = 0.0;
		double zeros = 0.0;
		for (int i = 0; i < result.length; i++) {
			if (result[i][1] == 1)
				ones++;
			else
				zeros++;
		}
		if (zeros >= ones)
			return 0;
		else
			return 1;
	}

	public static double[][] getDistance(double[][] features) {
		double[][] distancesFeature = new double[fileLength][2];
		double sum;
		for (int k = 0; k < fileLength; k++) {
			sum = 0;
			for (int i = 0; i < features[k].length - 1; i++) { // System.out.println(features1[i]+" "+features2[i]);
																// applied Euclidean distance formula
				sum += Math.pow(features[k][i] - candidate[i], 2);

			}
			distancesFeature[k][0] = Math.sqrt(sum);
			distancesFeature[k][1] = features[k][4];

		}
		return distancesFeature;
	}

	public void loadData(String filename) {

		File file = new File(filename);
		try {
			BufferedReader readFile = new BufferedReader(new FileReader(file));
			String line;
			int extendSize = 10;
			int i = 0;
			while ((line = readFile.readLine()) != null) {

				String[] split = line.split(",");
				if (i == 0) {

					features = new double[extendSize][split.length];
				} else if (i % extendSize == 0) {
					int newSize = i + extendSize;

					double[][] temp = new double[newSize][split.length];
					for (int j = 0; j < i; j++) {
						temp[j] = features[j];
					}
					features = temp;

				}
				for (int k = 0; k < split.length; k++) {
					features[i][k] = Double.parseDouble(split[k]);

				}

				i++;
			}
			fileLength = i;
			readFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("===================================== FILE NOT FOUND!!!!!!!!!!!!!!!! " + filename
					+ "=====================================");
			e.printStackTrace();
		} catch (Exception ex) {
			System.out.println(
					"===================================== AN EXCEPTION OCCURED!!!!!!!!!!!!!!!! =====================================");
			ex.printStackTrace();
		}
	}

	public void recruitment() {

		System.out.println("What will be the K");
		knn_value = sc.nextInt();
		System.out.println("Enter the social score");
		candidate[0] = sc.nextDouble();
		System.out.println("Enter the algorithm skill");
		candidate[1] = sc.nextDouble();
		System.out.println("Enter the gpa");
		candidate[2] = sc.nextDouble();
		System.out.println("Enter the age");
		candidate[3] = sc.nextDouble();

		loadData("data.txt");

		double[][] getOneArr = getDistance(features);

		double[][] sortedGetOne = Quickx2D.QuickSort(getOneArr);

		double[][] nearestNeighbours = nearestNeighbour(sortedGetOne);
		int count = count(nearestNeighbours);
		if (count == 1) {
			System.out.println("Accepted");
		} else if (count == 0) {
			System.out.println("Rejected");
		}

	}

	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		if (e.getSource().equals(btnresult)) {
			//System.out.println("What will be the K");
			//knn_value = sc.nextInt();
			//System.out.println("Enter the social score");
			candidate[0] = Double.parseDouble(txtAge.getText());
			//System.out.println("Enter the algorithm skill");
			candidate[1] = Double.parseDouble(txtSocialSkill.getText());
			//System.out.println("Enter the gpa");
			candidate[2] = Double.parseDouble(txtGpa.getText());
			//System.out.println("Enter the age");
			candidate[3] = Double.parseDouble(txtAlgorithmSkill.getText());

			loadData("data.txt");

			double[][] getOneArr = getDistance(features);

			double[][] sortedGetOne = Quickx2D.QuickSort(getOneArr);

			double[][] nearestNeighbours = nearestNeighbour(sortedGetOne);
			int count = count(nearestNeighbours);
			if (count == 1) {
				JOptionPane.showMessageDialog(rootPane, "Accepted", "Result", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Accepted");
			} else if (count == 0) {
				JOptionPane.showMessageDialog(rootPane, "Rejected", "Result", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Rejected");
			}
		}
		}
		catch (Exception e1) {
			// TODO: handle exception
		}
	}
}
