

//ZAD 1
class Time(var _hour: Int) {
  
  if (_hour < 0) _hour = 0
  
  //getter
  def hour = _hour
  
  //setter
  def hour_= (newHour: Int){ 
    if (newHour < 0) _hour = 0
    else _hour = newHour
  }
}


//obiekt towarzyszący
object Time{
  def apply(hour: Int) = new Time(hour)
}

//testy
val x = Time(5)  // x: Time = Time@749fdb04
val x = Time.apply(10)

x.hour  //  res13: Int = 5
x.hour = 2  // x.hour: Int = 2
x.hour = -10  //  x.hour: Int = 0





//ZAD 2

// a)


class Time(var _hour: Int, var _minute: Int){
  require(_hour < 24 || _hour >= 0)
  require(_minute < 60 || _minute >= 0)
  
  
  def hour = _hour
  
  def hour_= (newHour: Int){ 
    require(_hour < 24 || _hour >= 0)
    
    _hour = newHour
  }
  
  
  def minute = _minute
  
  def minute_= (newMinute: Int){ 
    require(_minute < 60 || _minute >= 0)
    
     _minute = newMinute
  }
  
  
  def before(other: Time): Boolean =
    _hour < other.hour || _hour == other.hour && _minute < other.minute 
  
}

val x = new Time(0, 43)
val y = new Time(1, 50)

x.before(y)  // Boolean = true



//b)

class Time(_hour: Int, _minute: Int){
  require(_hour < 24 || _hour >= 0)
  require(_minute < 60 || _minute >= 0)
  
  var _sumOfMinutes = _hour * 60 + _minute
  
  
  def hour: Int = _sumOfMinutes / 60
  
  def hour_= (newHour: Int){ 
    require(newHour < 24 || newHour >= 0)
    
    _sumOfMinutes = newHour * 60 + _minute
  }
  
  
  def minute: Int = _sumOfMinutes % 60
  
  def minute_= (newMinute: Int){ 
    require(_minute < 60 || _minute >= 0)
    
     _sumOfMinutes = hour + newMinute
  }
  
  
  def before(other: Time): Boolean =
    _sumOfMinutes < other._sumOfMinutes
}

