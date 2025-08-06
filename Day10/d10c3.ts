const employee=[
    { id: 1, name: "John Doe", position: "Software Engineer" },
    { id: 2, name: "Jane Smith", position: "Project Manager" },
    { id: 3, name: "Sam Brown", position: "UX Designer" },
    {id:4, name:"Alice Johnson", position:"Project Manager" },
];
const pos = employee
    .filter(emp => emp.position === "Project Manager")
    .map(emp => emp.name);
console.log("Project Managers:", pos); // Output: ["Jane Smith", "Alice Johnson"]