import React from 'react';
import { useHistory } from 'react-router-dom';

const Signup = ({state, signUp, handleChange}) => {

    const history = useHistory();

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
                <p id="firstname_error" className="valid_error hide">This field cannot be empty.</p>
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
                <p id="lastname_error" className="valid_error hide">This field cannot be empty.</p>
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
                <p id="password_error" className="valid_error hide">This field cannot be empty.</p>
                <p id="password_length_error" className="valid_error hide">Password should contain at least 6 characters and digits.</p>
                <p id="password_letter_error" className="valid_error hide">Password should contain at least 1 english letter.</p>
                <p id="password_digit_error" className="valid_error hide">Password should contain at least 1 digit.</p>
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
                <p id="repeat_password_error" className="valid_error hide">This field cannot be empty.</p>
                <p id="repeat_password_match_error" className="valid_error hide">Passwords do not match.</p>
                <p></p>
                <div>
                    <label htmlFor="id_type" id="id_type">ID type: </label>
                    <select 
                        name="id_type" 
                        value={state.signInfo.id_type}
                        onChange={handleChange}
                    >
                        <option value="us_passport">US passport</option>
                        <option value="driving_license">Driving license</option>
                    </select>
                </div>
                <p id="id_type_error" className="valid_error hide">This field cannot be empty.</p>
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
                <p id="id_number_error" className="valid_error hide">This field cannot be empty.</p>
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
                <p id="address_error" className="valid_error hide">This field cannot be empty.</p>
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
                <p id="mobile_phone_error" className="valid_error hide">This field cannot be empty.</p>
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
                <p id="home_phone_error" className="valid_error hide">This field cannot be empty.</p>
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