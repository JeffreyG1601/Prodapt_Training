$num1 = Read-Host "Enter the first number"
$num2 = Read-Host "Enter the second number"
Write-Output "Select an operation:"
    Write-Output "1. Addition "
    Write-Output "2. Subtraction"
    Write-Output "3. Multiplication"
    Write-Output "4. Division"

$choice = Read-Host "Enter your choice (1-4)"

switch ($choice) {
    1 {
        $result = [int]$num1 + [int]$num2
        Write-Output "Result: $result"
    }
    2{
        $result = [int]$num1 - [int]$num2
        Write-Output "Result: $result"
    }
    3 {
        $result = [int]$num1 * [int]$num2
        Write-Output "Result: $result"
    }
    4 {
        if ($num2 -eq 0) {
            Write-Output "Error: Division by zero is not allowed."
        } else {
            $result = [int]$num1 / [int]$num2
        Write-Output "Result: $result"
        }
    }
    default {
        Write-Host "Invalid choice. Please select a number between 1 and 4."
    }
}
