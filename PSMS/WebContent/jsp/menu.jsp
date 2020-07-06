%@page import="java.util.Calendar" %>    


<%@ page import="java.text.*,java.util.*" session="true"%>
    <header class="mdl-layout__header">
    	<div class="mdl-layout__header-row">
    	
    	<span class="mdl-layout-title"> Personal Stuff</span>	
    	
    	<div class="mdl-layout-spacer"></div>
    	<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm"); %>
		<h5><%= df.format(new java.util.Date()) %> </h5>
		
    
    		
    	<nav class="mdl-navigation mdl-layout--large-screen-only">
    	<a class= "mdl-navigation__link" href="/PSMS_ver1/StuffController?op=new">Add new staff</a>
    	<a class= "mdl-navigation__link" href="/PSMS_ver1/StuffController?op=list">List new staff</a>
    	</nav>	
    	</div>
        </header>
        
        <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">PSMS</span>
        <nav class= "mdl-navigation">
        <a class ="mdl-navigation__link" href="/PSMS_ver1/StuffController?op=new">Add new stuff</a>
        <a class ="mdl-navigation__link" href="/PSMS_ver1/StuffController?op=list">List all stuff</a>
        
        </nav>
        </div>