package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import static java.lang.Math.*;


@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String a; //wartosc kredytu
	private double numer_a =0;
	private String b; //oprocentowanie
	private double numer_b =0;
	private Double result;
	private String n; // ilosc rat w miseiacach
	private double numer_n =0;
	private Double x;
	private Double q;
	
	
	@Inject
	FacesContext ctx;



	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
			try {
				numer_a = Double.parseDouble(a);
			} catch(NumberFormatException nfe)
			{}
		}


	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
		try {
			numer_b = Double.parseDouble(b);
		} catch(NumberFormatException nfe)
		{}
	}

	public Double getResult() {
		return result;
	}


	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
		try {
			numer_n = Double.parseDouble(n);
		} catch(NumberFormatException nfe)
		{}
	}

	private void doTheMath() {
		
			numer_b = numer_b/100;
			q = 1+(numer_b/12);
			x = pow(q,numer_n);
			
			result = ((numer_a*(x)*(q-1))/((x)-1))*numer_n ;
	}


	public String doCalc() {
		doTheMath(); 
		return ("showresult");
	}
	
	/*
	 * // Put result in messages on AJAX call public String calc_AJAX() { if
	 * (doTheMath()) { ctx.addMessage(null, new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null)); } return
	 * null; }
	 */

}
