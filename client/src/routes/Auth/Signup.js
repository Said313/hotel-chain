import React from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import serverPath from '../../api/path';

const Signup = () => {

    const history = useHistory();

    const signUp = (form) => {
        axios.post(`${serverPath}/services/items`, {
                firstname: form.querySelector('#firstname').value,
                lastname: form.querySelector('#lastname').value,
                password: form.querySelector('#password').value,
                repeat_password: form.querySelector('#repeat_password').value,
                id_type: form.querySelector('#id_type').value,
                id_number: form.querySelector('#id_number').value,
                address: form.querySelector('#address').value,
                mobile_phone: form.querySelector('#mobile_phone').value,
                home_phone: form.querySelector('#home_phone').value,
                category: form.querySelector('#category').value,
            })
            .catch((error)=>{
                window.alert("Cannot access the server!")
            })
            .then((res)=>{
                history.push('/');
            });
    }

    return (
        <div>
            <h3>Sign up</h3>
            <form onSubmit={(e)=>{
                    e.preventDefault();
                    signUp(e.target);
                }}>
                <div>
                    <label htmlFor="firstname">Firstname: </label>
                    <input type="text" id="firstname" name="firstname"/>
                </div>
                <div>
                    <label htmlFor="lastname">Lastname: </label>
                    <input type="text" id="lastname" name="lastname"/>
                </div>
                <div>
                    <label htmlFor="password">Password: </label>
                    <input type="password" id="password" name="password"/>
                </div>
                <div>
                    <label htmlFor="repeat_password">Repeat Password: </label>
                    <input type="password" id="repeat_password" name="repeat_password"/>
                </div>
                <div>
                    <label htmlFor="id_type">ID type: </label>
                    <input type="text" id="id_type" name="id_type"/>
                </div>
                <div>
                    <label htmlFor="id_number">ID Number: </label>
                    <input type="text" id="id_number" name="id_number"/>
                </div>
                <div>
                    <label htmlFor="address">Address: </label>
                    <input type="text" id="address" name="address"/>
                </div>
                <div>
                    <label htmlFor="mobile_phone">Mobile phone: </label>
                    <input type="text" id="mobile_phone" name="mobile_phone"/>
                </div>
                <div>
                    <label htmlFor="home_phone">Home phone: </label>
                    <input type="text" id="home_phone" name="home_phone"/>
                </div>
                <div>
                    <label htmlFor="category">Category: </label>
                    <input type="text" id="category" name="category"/>
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default Signup;