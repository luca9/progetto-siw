<%@taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Product confirm</title>
</head>
<body>
<div>Name: #{productController.product.name}</div>
<br/>
<div>Code: #{productController.product.code}</div>
<br/>
<div>Description: #{productController.product.description}</div>
<br/>
<div>Price: #{productController.product.price}</div>
<br/>
<h2>Confirm?</h2>
<input type="checkbox" name="yes" value="Yes"> Yes 
<input type="checkbox" name="no" value="No">  
<input type="submit" name="submit" value="invia"> Submit 
</body>
</html>
