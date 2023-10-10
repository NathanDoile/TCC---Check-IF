import { TelaInicial } from "../ui/screen/";

const { createBrowserRouter } = require("react-router-dom");
  
export const router = createBrowserRouter([
    {
        path: "/",
        element: <TelaInicial />,
      },
]);
  