def reverse(s :String) = (for(i<-s.length-1 to 0 by -1) yield s(i)).mkString

def iter() = {
    args.foreach(arg => print(arg.reverse.toUpperCase+" "))
}

iter()
