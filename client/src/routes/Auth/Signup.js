import React from 'react';
import { useHistory } from 'react-router-dom';
import { useForm } from 'react-hook-form'
import axios from 'axios';

import serverPath from '../../api/path';

const Signup = () => {

    const history = useHistory();
    const { register, errors, watch, handleSubmit } = useForm();

    const errorMessage = errMessage => {
        return <p className="validError">{errMessage}</p>
    }

    const onSubmit = formData => {
        console.log(formData);
        axios.post(`${serverPath}/services/auth/signup`, {
            firstname: formData.firstname,
            lastname: formData.lastname,
            password: formData.password,
            id_type: formData.idType,
            id_number: formData.idNumber,
            address: formData.address,
            mobile_phone: formData.mobilePhone,
            home_phone: formData.homePhone,
            category: formData.category,
          })
          .then((res)=>{
            window.alert("You are signed up successfully!");
            history.push('/login');
          })
          .catch((error)=>{
            window.alert("Cannot access the server!");
            console.log(error);
          });
          
    }

    return (
        <div>
            <h3>Sign up</h3>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <label htmlFor="firstname">Firstname: </label>
                    <input 
                        type="text" 
                        id="firstname" 
                        name="firstname" 
                        ref={register({required: true})}
                    />
                </div>
                {errors.firstname && errorMessage('Firstname is required!')}
                <div>
                    <label htmlFor="lastname">Lastname: </label>
                    <input 
                        type="text" 
                        id="lastname" 
                        name="lastname"
                        ref={register({required: true})}
                    />
                </div>
                {errors.lastname && errorMessage('Lastname is required!')}
                <div>
                    <label htmlFor="password">Password: </label>
                    <input 
                        type="password" 
                        id="password" 
                        name="password"
                        ref={register({minLength: 6, validate: {
                            letters: value => /[a-zA-Z]/.test(value),
                            digits: value => /[0-9]/.test(value),
                        }})}
                    />
                </div>
                {errors.password && errors.password.type === "minLength" && errorMessage('Password should contain at least 6 characters!')}
                {errors.password && errors.password.type === "letters" && errorMessage('Password should contain at least 1 english letter!')}
                {errors.password && errors.password.type === "digits" && errorMessage('Password should contain at least 1 digit!')}
                <div>
                    <label htmlFor="repeat_password">Repeat Password: </label>
                    <input 
                        type="password" 
                        id="repeatPassword" 
                        name="repeatPassword"
                        ref={register({validate: {
                            confirmPassword: value => value === watch('password'),
                        }})}
                    />
                </div>
                {errors.repeatPassword && errors.repeatPassword.type === "confirmPassword" && errorMessage('Passwords do not match!')}
                <p></p>
                <div>
                    <label htmlFor="idType" id="id_type">ID type: </label>
                    <select 
                        name="idType"
                        ref={register({required: true})}
                    >
                        <option value="">Choose ID Type</option>
                        <option value="us_passport">US passport</option>
                        <option value="driving_license">Driving license</option>
                    </select>
                </div>
                {errors.idType && errorMessage('Choose ID Type')}
                <div>
                    <label htmlFor="id_number">ID Number: </label>
                    <input 
                        type="text" 
                        id="idNumber" 
                        name="idNumber"
                        ref={register({required: true})}
                    />
                </div>
                {errors.idNumber && errorMessage('ID Number is required!')}
                <div>
                    <label htmlFor="address">Address: </label>
                    <input 
                        type="text" 
                        id="address" 
                        name="address"
                        ref={register({required: true})}

                    />
                </div>
                {errors.address && errorMessage('Address is required!')}
                <div>
                    <label htmlFor="mobile_phone">Mobile phone: </label>
                    <input 
                        type="tel" 
                        id="mobilePhone" 
                        name="mobilePhone"
                        ref={register({required: true})}
                    />
                </div>
                {errors.mobilePhone && errorMessage('Mobile phone is required!')}
                <div>
                    <label htmlFor="home_phone">Home phone: </label>
                    <input 
                        type="tel" 
                        id="homePhone" 
                        name="homePhone"
                        ref={register({required: true})}
                    />
                </div>
                {errors.homePhone && errorMessage('Home phone is required!')}
                <div>
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
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default Signup;