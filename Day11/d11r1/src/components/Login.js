import "../styles/login.css"
import Signup from './Signup'; // Assuming you have a Signup component
import { useNavigate } from 'react-router-dom';

function Login(){
    const navigate = useNavigate();

    return (
        <div className="login-container">
            <h1>Login Page</h1>
            <form>
                <label>
                    Username:
                    <input type="text" name="username" />
                </label>
                <br />
                <label>
                    Password:
                    <input type="password" name="password" />
                </label>
                <br />
                <button type="submit">Login</button>
                <button type="button" onClick={() => navigate('/signup')}>Signup</button>
            </form>
        </div>
    );
}

export default Login;