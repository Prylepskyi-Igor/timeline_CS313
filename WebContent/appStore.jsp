<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">

      function activateTab(pageId) {
          var tabCtrl = document.getElementById('tabCtrl');
          var pageToActivate = document.getElementById(pageId);
          for (var i = 0; i < tabCtrl.childNodes.length; i++) {
              var node = tabCtrl.childNodes[i];
              if (node.nodeType == 1) { /* Element */
                  node.style.display = (node == pageToActivate) ? 'block' : 'none';
              }
          }
      }

    </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>App Store</title>
</head>
<body style="background-color: grey; padding: 15px;">
	<h1 style="
	background: radial-gradient( 5px -9px, circle, white 8%, red 26px );
    background-color: red;
    border: 2px solid white;
    border-radius: 12px; /* one half of ( (border * 2) + height + padding ) */
    box-shadow: 1px 1px 1px black;
    color: white;
    font: bold 15px/13px Helvetica, Verdana, Tahoma;
    height: 25px; 
    min-width: 14px;
    max-width: 100px;
    padding: 4px 3px 0 3px;
    text-align: center;
    margin: 0 auto;
">App Store</h1>
	<div style="margin: 0 auto; width: 300px; background: grey; padding: 20px; color: white;"><ul>
      <li style="display: inline;">
        <button type="button" class="btn btn-success" onclick="javascript:activateTab('page1')">Description</button>
      </li>
      <li style="display: inline;">
        <button type="button" class="btn btn-primary" onclick="javascript:activateTab('page2')">Try it now</button>
      </li>
    </ul></div>
    <div id="tabCtrl">
      <div id="page1" style="display: none; text-align: center;">This app is supposed to display universal timeline with data from Facebook, Twitter and Instagram.</div>
      <div id="page2" style="display: none; text-align: center;"><a href="SignInServlet?empty=">Access my Universal Timeline</a></div>
    </div>
</body>
</html>