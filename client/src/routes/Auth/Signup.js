import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useForm } from 'react-hook-form'
import axios from 'axios';

import serverPath from '../../api/path';

const Signup = () => {

    const history = useHistory();
    const { register, errors, watch, handleSubmit } = useForm({mode:"onChange"});
    const [validLogin, setValidLogin] = useState(true);

    const errorMessage = errMessage => {
        return <p className="validError">{errMessage}</p>
    }

    const handleLogin = ({target}) => {
        axios.post(`${serverPath}/services/auth/checkLogin`, {
            login: target.value,
        })
            .then(res => {
                setValidLogin(res.data);
            })
            .catch(error => {
                window.alert("Cannot access the server!");
                console.log(error);
            });
    }

    const onSubmit = formData => {
        if(validLogin){
            const loginValue = document.getElementById('signLogin').value;
            axios.post(`${serverPath}/services/auth/signup`, {
                firstname: formData.firstname,
                lastname: formData.lastname,
                password: formData.password,
                login: loginValue,
                id_type: formData.idType,
                id_number: formData.idNumber,
                address: formData.address,
                mobile_phone: formData.mobilePhone,
                home_phone: formData.homePhone,
                category: formData.category,
              })
              .then(res => {
                window.alert("You are signed up successfully!");
                history.push('/login');
              })
              .catch(error => {
                window.alert("Cannot access the server!");
                console.log(error);
              });    
        }
        
    }

    return (
        <div className="Signup">
            <h3>Sign up</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <input 
                        type="text" 
                        className="signInput" 
                        name="firstname"
                        placeholder="Firstname"
                        required
                        ref={register({required: true})}
                    />
                </div>
                {errors.firstname && errorMessage('Firstname is required!')}

                <div>
                    <input 
                        type="text" 
                        className="signInput"
                        name="lastname"
                        placeholder="Lastname"
                        required
                        ref={register({required: true})}
                    />
                    
                </div>
                {errors.lastname && errorMessage('Lastname is required!')}
                <div>
                    <input 
                        type="password" 
                        className="signInput"
                        name="password"
                        placeholder="Password"
                        ref={register({minLength: 6, validate: {
                            letters: value => /[a-zA-Z]/.test(value),
                            digits: value => /[0-9]/.test(value),
                        }})}
                    />
                    
                </div>
                {errors.password && errors.password.type === "letters" && errorMessage('Password should contain at least 1 english letter!')}
                {errors.password && errors.password.type === "minLength" && errorMessage('Password should contain at least 6 characters!')}
                {errors.password && errors.password.type === "digits" && errorMessage('Password should contain at least 1 digit!')}
                <div>
                    <input 
                        type="password" 
                        className="signInput"
                        name="repeatPassword"
                        placeholder="Re-type password"
                        ref={register({validate: {
                            confirmPassword: value => value === watch('password'),
                        }})}
                    />
                    
                </div>
                {errors.repeatPassword && errors.repeatPassword.type === "confirmPassword" && errorMessage('Passwords do not match!')}
                <div>
                    <input 
                        type="text" 
                        className="signInput"
                        name="signLogin"
                        placeholder="Login"
                        required
                        onChange={handleLogin}
                    />
                    
                </div>
                {!validLogin && errorMessage('This login is not available!')}
                <div>
                    <input 
                        type="text"
                        id="signLogin"
                        className="signInput"
                        name="address"
                        placeholder="Address"
                        required
                        ref={register({required: true})}
                    />
                    
                </div>
                {errors.address && errorMessage('Address is required!')}
                <div className="signIdType">
                    <label htmlFor="idType" id="id_type">ID type: </label>
                    <select 
                        name="idType"
                        required
                        ref={register({required: true})}
                    >
                        <option value=""></option>
                        <option value="us_passport">US passport</option>
                        <option value="driving_license">Driving license</option>
                    </select>
                    
                </div>
                {errors.idType && errorMessage('Choose ID Type')}
                <div>
                    <input 
                        type="text" 
                        className="signInput"
                        name="idNumber"
                        placeholder="ID number"
                        required
                        ref={register({required: true})}
                    />
                    
                </div>
                {errors.idNumber && errorMessage('ID Number is required!')}
                <div>
                    <input 
                        type="tel" 
                        className="signInput"
                        name="mobilePhone"
                        placeholder="Mobile phone"
                        required
                        ref={register({required: true})}
                    />
                    
                </div>
                {errors.mobilePhone && errorMessage('Mobile phone is required!')}
                <div>
                    <input 
                        type="tel" 
                        className="signInput"
                        name="homePhone"
                        placeholder="Home phone"
                        required
                        ref={register({required: true})}
                    />
                    
                </div>
                {errors.homePhone && errorMessage('Home phone is required!')}
                <div className="signCategory">
                    <label htmlFor="category">Category: </label>
                    <div id="category">
                        <input 
                            type="radio" 
                            id="cat_none"
                            value=""
                            ref={register}
                            name="category"
                        />
                        <label htmlFor="cat_none">None</label>
                        <input 
                            type="radio" 
                            id="cat_military"
                            value="military"
                            ref={register}
                            name="category"
                        />
                        <label htmlFor="cat_military">Military</label>
                        <input 
                            type="radio"
                            id="cat_vip"
                            value="vip"
                            ref={register}
                            name="category"
                        />
                        <label htmlFor="cat_vip">Vip</label>
                    </div>
                </div>
                <div>
                    <button type="submit" className="signupButton">Submit</button>
                </div>
            </form>
        </div>
    );
}

export default Signup;