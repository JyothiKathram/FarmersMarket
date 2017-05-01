<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<h1><center><i>Connecticut Farmers Market</i></center></h1>
<h2><center><i>Farmer</i></center></h2>
		
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script type="text/javascript">

	function farmer_validate(){
	
		var Addcrop = document.getElementById('Addcrop').value;
		var Updateqty = document.getElementById('Updateqty').value;
	    var Declareprice = document.getElementById('Declareprice').value;
	   
	
	if(Addcrop=="" || Updateqty=="" || Declareprice==""){
		
		alert('Please fill in all fields, fields cannot be empty');
		return false;
	}
	else{	
	   	 return true;     
	   	}
	   
	  
	}
	
	
</script>

</head>
<body  bgcolor="#ccffcc" >
<style type="text/css">

.topRight {
	float: right;
}
</style>	
	
<div class="topRight">
		Welcome ${userid}	
</div>



<style type="text/css">
 .topMenu {
 float: left;
 }
 .bottomMenu {
 float: right;
 line-height:2;
  width: 40%;
 }
</style>
<div class="topMenu ">
<img src="C:\Users\jyothisrikanth\Desktop\Jyothi_UNH\Sem2\Library\Farmers Market/Images/FM5.png"/>
</div>
<div class="bottomMenu">



<form action="farmerfunction_validate"  method="POST">		

<center>Add Crop <select id="Addcrop" name="Addcrop" style="width: 100px; height: 25px;">
<option selected disabled>Crop type</option>
<option value="Rice">Rice</option>
<option value="Wheat">Wheat</option>
<option value="Tomatoes">Tomatoes</option>
<option value="Potato">Potato</option>
<option value="Green Grapes">Green Grapes</option>
<option value="Red Grapes">Red Grapes</option>
<option value="Strawberries">Strawberries</option>
<option value="Apples">Apples</option>
<option value="Green Apples">Green Apples</option>
<option value="Oranges">Oranges</option>
<option value="Watermelon">Watermelon</option>
<option value="Corn">Corn</option></select></center>
<center>Update Quantity<input type="text" name="Updateqty" id="Updateqty"/></center>
<center>Declare Price<input type="text" name="Declareprice" id="Declareprice"/></center>	
<br><center><input type="submit" value="submit" onClick="return farmer_validate();"/></center>
<span style="color:red;">${errMsg_f1}</span>

</form>
</div>
</body>
</html>
