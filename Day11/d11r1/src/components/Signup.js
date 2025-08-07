import "../styles/signup.css"
function Signup() {
    return (
        <div className="signup-container">
            <h1>Signup Page</h1>
            <form>
                <label>
                    Username:
                    <input type="text" name="username" />
                </label>
                <br />
                <label>
                    Email:
                    <input type="email" name="email" />
                </label>
                <br />
                <label>
                    DoB:
                    <input type="date" name="dob" />
                </label>
                <label>
                    Password:
                    <input type="password" name="password" />
                </label>
                <br />
                <button type="submit">Signup</button>
            </form>
        </div>
    );
}

export default Signup;