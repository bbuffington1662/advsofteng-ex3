package ex03;

import java.util.Scanner;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

public class Driver {
	public static void main(String hi[]) {
		String args[] = null;
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter the class to insert print lines: ");
		args = input.nextLine().split(" ");
		while (args.length != 1) {
			System.out.println("[WRN] Invalid Input");
			System.out.print("Please enter the class to insert print lines: ");
			args = input.nextLine().split(" ");
		}
		input.close();
		
		try {
	         ClassPool cp = ClassPool.getDefault();
	         CtClass cc = cp.get(args[0]);

	         CtConstructor declaredConstructor = cc.getDeclaredConstructor(new CtClass[0]);
	         String block1 = "System.out.println(\"id: \" + id);";
	         System.out.println("[DBG] BLOCK1: " + block1);
	         declaredConstructor.insertAfter(block1);

	         String block2 = "System.out.println(\"year: \" + year);";
	         System.out.println("[DBG] BLOCK2: " + block2);
	         declaredConstructor.insertAfter(block2);
	         
	         Class<?> c = cc.toClass();
	         c.newInstance();

	      } catch (NotFoundException | CannotCompileException | InstantiationException | IllegalAccessException e) {
	         System.out.println(e.toString());
	      }
	}
}
