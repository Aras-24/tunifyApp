import { createContext, useContext } from "react";

export const AuthContext = createContext();
export const useAuth = () => {
    const context = useContext(AuthContext);
    if(!context){
        throw new Error('useAuth darf nur innerhalb eines AuthProvider verwendet werden')
    }
    return context;
}

export const AuthProvider = ({children}) => {

    const contextValue = {

    }

    retutn(
      <AuthContext.Provider value={contextValue}>
        {children}
      </AuthContext.Provider>
    )
}