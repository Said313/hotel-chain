import React from 'react';
import { useHistory } from 'react-router-dom';

const Signup = ({state, signUp, handleChange}) => {

    const history = useHistory();

    const errorMessage = (errorName) => {
        return state.signErrors[errorName].length > 0 ? (
            <p className="valid_error">{state.signErrors[errorName]}</p>
        ) : "";
    }

    return (
        <div>
            <h3>Sign up</h3>
            <form onSubmit={(e)=>{
                    e.preventDefault();
                    signUp(e.target);
                    history.push('/');
                }}>
                <div>
                    <label htmlFor="firstname">Firstname: </label>
                    <input 
                        type="text" 
                        id="firstname" 
                        name="firstname" 
                        value={state.signInfo.firstname}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('firstname')}
                <div>
                    <label htmlFor="lastname">Lastname: </label>
                    <input 
                        type="text" 
                        id="lastname" 
                        name="lastname"
                        value={state.signInfo.lastname}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('lastname')}
                <div>
                    <label htmlFor="password">Password: </label>
                    <input 
                        type="password" 
                        id="password" 
                        name="password"
                        value={state.signInfo.password}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('password1')}
                {errorMessage('password2')}
                {errorMessage('password3')}
                <div>
                    <label htmlFor="repeat_password">Repeat Password: </label>
                    <input 
                        type="password" 
                        id="repeat_password" 
                        name="repeat_password"
                        value={state.signInfo.repeat_password}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('repeat_password')}
                <p></p>
                <div>
                    <label htmlFor="id_type" id="id_type">ID type: </label>
                    <select 
                        name="id_type" 
                        value={state.signInfo.id_type}
                        onChange={handleChange}
                    >
                        <option value="">Choose ID Type</option>
                        <option value="us_passport">US passport</option>
                        <option value="driving_license">Driving license</option>
                    </select>
                </div>
                {errorMessage('id_type')}
                <div>
                    <label htmlFor="id_number">ID Number: </label>
                    <input 
                        type="text" 
                        id="id_number" 
                        name="id_number"
                        value={state.signInfo.id_number}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('id_number')}
                <div>
                    <label htmlFor="address">Address: </label>
                    <input 
                        type="text" 
                        id="address" 
                        name="address"
                        value={state.signInfo.address}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('address')}
                <div>
                    <label htmlFor="mobile_phone">Mobile phone: </label>
                    <input 
                        type="tel" 
                        id="mobile_phone" 
                        name="mobile_phone"
                        value={state.signInfo.mobile_phone}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('mobile_phone')}
                <div>
                    <label htmlFor="home_phone">Home phone: </label>
                    <input 
                        type="tel" 
                        id="home_phone" 
                        name="home_phone"
                        value={state.signInfo.home_phone}
                        onChange={handleChange}
                    />
                </div>
                {errorMessage('home_phone')}
                <div>
                    <label htmlFor="category">Category: </label>
                    <div id="category">
                        <input 
                            type="radio" 
                            id="cat_military"
                            value="military"
                            checked={state.signInfo.category === "military"}
                            onChange={handleChange}
                            name="category"
                        />
                        <label htmlFor="cat_military">Military</label>
                        <input 
                            type="radio"
                            id="cat_vip"
                            value="vip"
                            checked={state.signInfo.category === "vip"}
                            onChange={handleChange}
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