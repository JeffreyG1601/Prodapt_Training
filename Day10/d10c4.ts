function calculateSum(a: number, b: number, callback: (result: number) => void) {
  const sum = a + b;
  callback(sum);
}
function displayResult(result: number) {
  console.log('The sum is:', result);
}

// Using callback: pass displayResult as a callback to calculateSum
calculateSum(5, 7, displayResult); // Output: The sum is: 12
