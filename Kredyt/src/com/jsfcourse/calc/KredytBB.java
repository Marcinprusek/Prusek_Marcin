package com.jsfcourse.calc;

import javax.inject.Inject;
import static java.lang.Math.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String a; //wartosc kredytu	
	private String b; //oprocentowanie
	private Double result;
	private String n; // ilosc rat w miseiacach
	private Double x;
	private Double q;
	
	@Inject
	FacesContext ctx;



	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public Double getResult() {
		return result;
	}


	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public boolean doTheMath() {
		try {
			double a = Double.parseDouble(this.a);
			double b = Double.parseDouble(this.b);
			double n = Double.parseDouble(this.n);
			
			b = b/100;
			q = 1+(b/12);
			x = pow(q,n);
			
			result = ((a*(x)*(q-1))/((x)-1))*n ;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return false;
		}
	}




	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}
	
	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

}
