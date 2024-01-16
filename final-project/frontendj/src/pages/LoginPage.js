

import Login from "../components/Login";

function LoginPage() {
    return (
        <div className="login-container" style={{ padding: '90px' }}>
            <header className="login-menu">

                <h1>Login</h1>

                <Login/>
            </header>
        </div>

    );
}

export default LoginPage;
