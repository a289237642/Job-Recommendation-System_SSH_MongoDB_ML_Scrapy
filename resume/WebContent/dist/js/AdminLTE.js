// JavaoScript Document
var oDiv1 = document.getElementById("content");
var oDiv2 = document.getElementById("backstageShow");
var oDiv3 = document.getElementById("userInterface");

function onclick1()
{
	if(oDiv1.style.display == 'none')
	{
		oDiv1.style.display = "block";
		oDiv2.style.display = "none";
		oDiv3.style.display = "none";
	}
	else
	{
		oDiv2.style.display = "none";
		oDiv3.style.display = "none";
	}
}

function onclick2()
{
	if(oDiv2.style.display == 'none')
	{
		oDiv1.style.display = "none";
		oDiv2.style.display = "block";
		oDiv3.style.display = "none";
	}
	else
	{
		oDiv1.style.display = "none";
		oDiv2.style.display = "block";
		oDiv3.style.display = "none";
	}
}

function onclick3()
{
	if(oDiv3.style.display == 'none')
	{
		oDiv1.style.display = "none";
		oDiv2.style.display = "none";
		oDiv3.style.display = "block";
	}
	else
	{
		oDiv1.style.display = "none";
		oDiv2.style.display = "none";
	}
}




