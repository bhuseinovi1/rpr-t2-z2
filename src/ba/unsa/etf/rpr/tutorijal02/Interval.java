package ba.unsa.etf.rpr.tutorijal02;
public class Interval {
    private double pt,kt;
    private boolean prip_pt,prip_kt;
    public Interval (double pt,double kt,boolean prip_pt,boolean prip_kt) {
        if(pt>kt) throw new IllegalArgumentException();
        this.pt=pt;
        this.kt=kt;
        this.prip_pt=prip_pt;
        this.prip_kt=prip_kt;
    }
    public Interval() {
        this(0,0,false,false);
    }
    public double dajPt() {
        return pt;
    }
    public double dajKt() {
        return kt;
    }
    public boolean isNull() {
        if(this.pt==0 && this.kt==0 && this.prip_kt==false && this.prip_pt==false) return true;
        return false;
    }
    public boolean isIn(double tacka) {
        if(prip_pt && prip_kt) {
            return (tacka>=pt && tacka<=kt);
        }
        else if(!prip_pt && !prip_kt) {
            return (tacka>pt && tacka<kt);
        }
        else if(prip_pt) {
            return (tacka>=pt && tacka<kt);
        }
        else {
            return (tacka>pt && tacka <kt);
        }
    }
    public Interval intersect(Interval interval) {
        Interval povratni = new Interval();
        if(interval.pt==this.pt) {
            povratni.pt=this.pt;
            if(interval.prip_pt && this.prip_pt) {
                povratni.prip_pt=true;
            }
            else {
                povratni.prip_pt=false;
            }
        }
        if(interval.kt==this.kt) {
            povratni.kt=this.kt;
            if(interval.prip_kt && this.prip_kt) {
                povratni.prip_kt=true;
            }
            else {
                povratni.prip_kt=false;
            }
        }
        if(interval.pt>this.pt && interval.kt<this.kt) {
            povratni=interval;
        }

        else if(interval.pt<this.pt && interval.kt>this.kt) {
            povratni=this;
        }
        else if(interval.pt>this.pt && interval.kt>this.kt) {
            povratni.pt=interval.pt;
            povratni.kt=this.kt;
            povratni.prip_pt=interval.prip_pt;
            povratni.prip_kt=this.prip_kt;
        }
        else if(interval.pt<this.pt && interval.kt<this.kt) {
            povratni.pt=this.pt;
            povratni.kt=interval.kt;
            povratni.prip_pt=this.prip_pt;
            povratni.prip_kt=interval.prip_kt;
        }
        return povratni;
    }
    public static Interval intersect(Interval interval1,Interval interval2) {
        Interval povratni = interval1.intersect(interval2);
        return povratni;
    }

    @Override
    public String toString() {
        if(this.isNull()) {
            return String.format("()");
        }
        if(this.prip_pt && this.prip_kt) {
            return String.format("[" + this.pt + "," + this.kt + "]");
        }
        else if(!this.prip_pt && !this.prip_kt) {
            return String.format("(" + this.pt + "," + this.kt + ")");
        }
        else if(this.prip_pt) {
            return String.format("[" + this.pt + "," + this.kt + ")");
        }
        else {
            return String.format("(" + this.pt + "," + this.kt + "]");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Interval)) {
            return false;
        }
        Interval inter = (Interval) o;
        return Double.compare(pt, inter.pt) == 0
                && Double.compare(kt, inter.kt) == 0 && prip_pt==inter.prip_pt && prip_kt==inter.prip_kt;
    }

}
