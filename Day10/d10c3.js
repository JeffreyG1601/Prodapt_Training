var employee = [
    { id: 1, name: "John Doe", position: "Software Engineer" },
    { id: 2, name: "Jane Smith", position: "Project Manager" },
    { id: 3, name: "Sam Brown", position: "UX Designer" },
    { id: 4, name: "Alice Johnson", position: "Project Manager" },
];
var pos = employee
    .filter(function (emp) { return emp.position === "Project Manager"; })
    .map(function (emp) { return emp.name; });
console.log("Project Managers:", pos); // Output: ["Jane Smith", "Alice Johnson"]
