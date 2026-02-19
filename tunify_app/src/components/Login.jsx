import toast from "react-hot-toast";
import { assets } from "../assets/assets";
import React, { useState } from "react";

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);   
  const [error, setError] = useState('');

  const handleSubmit = async (e) =>{
    e.preventDefault();
    setError('');   

    if(!email || !password){
      const msg = 'Bitte füllen Sie alle Felder aus';
      setError(msg);
      toast.error(msg);
    }

    console.log(email,password);

  }
  return (
    <div className="min-h-screen bg-gradient-to-br from-green-900 via-black to-green-900 flex items-center justify-center p-4">
      <div className="max-w-md w-full space-8">
        {/* Header */}
        <div className="text-center">
          <div className="flex items-center justify-center mb-6">
            <div className="flex items-center judtify-center mb-6">
              <img src={assets.logo} alt="logo" className="w-16 h-16" />
              <h1 className="ml-3 text-3xl font-bold text-white">Tunify</h1>
            </div>
          </div>
          <p className="text-white text-xl">Schön, dass du da bist!</p>
          <p className="text-gray-300">
            Melde dich an und genieße Musik ohne Grenzen
          </p>
        </div>
        {/* Register form */}
        <div className="bg-gray-900/80 backdrop-blur-lg rounded-2xl p-8 shadow-2xl border-gray-700 mt-6">
          <form className="space-y-6" onSubmit={handleSubmit}>
            {/* E-Mail Field */}
            <div>
              <label
                htmlFor="email"
                className="block text-sm font-medium text-gray-200 mb-1"
              >
                E-Mail Adresse
              </label>
              <input
                type="text"
                name="email"
                id="email"
                autoComplete="email"
                required
                className="block w-full px-4 py-3 border border-gray-600 rounded-lg bg-gray-800/50 text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent transition-all duration-200"
                placeholder="Deine E-Mail Adresse"
                value={email}
                onChange={e => setEmail(e.target.value)}
              />
            </div>

            {/* Password field*/}
            <div>
              <label
                htmlFor="password"
                className="block text-sm font-medium text-gray-200 mb-1 mt-4"
              >
                Passwort
              </label>
              <input
                type="password"
                name="password"
                id="password"
                autoComplete="new-password"
                required
                className="block w-full px-4 py-3 border border-gray-600 rounded-lg bg-gray-800/50 text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent transition-all duration-200"
                placeholder="Passwort eingeben"
                value={password}
                onChange={e => setPassword(e.target.value)}
              />
            </div>

            {/* Submit button*/}
            <button className="w-full flex justify-center py-3 px-4 mt-6 border border-transparent rounded-lg shadow-sm text-sm font-medium text-white bg-green-500 hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 disabled:opacity-50 disabled:cursor-not-allowed trsndtion-sall duration-200 transform hover:scale-105">
              Anmelden
            </button>

            {/* Switch to Register */}
            <div className="mt-6 text-center">
              <p className="text-sm text-gray-400">
                Du hast noch keinen Account? Jetzt{" "}
                <button className="text-green-400 hover:text-green-300 font-medium transition-colors cursor-pointer">
                  registrieren
                </button>
              </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};
export default Login;
