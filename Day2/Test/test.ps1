$a = Read-Host "Enter A"
$b = Read-Host "Enter B"
$a=[int]$a
$b=[int]$b

if ($a -gt $b){
	echo "Maximum is : $a" }
else{
    	echo "Maximum is :$b" }