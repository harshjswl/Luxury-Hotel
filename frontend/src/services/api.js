import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:9090/api",
});

API.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});


export const RegisterUser = (credentials) =>
  API.post("/auth/register", credentials);

export const OtpVerification = (credentials) =>
  API.post("/auth/verify-otp", credentials);

export const loginUser = (credentials) =>
  API.post("/auth/login", credentials);

export const forgotPassword = (credentials)=>
  API.post("/auth/forgot-password", credentials);


export const setPassword=(credentials)=>
  API.post("/auth/reset-password",credentials);