import Register from "./components/Register";
import Login from "./components/Login";
import { Toaster } from "react-hot-toast";


const App = () => {
    return(
        <div>
            <Toaster/>
            <Login/>
            <Register/>
        </div>
        )
    }

export default App;
