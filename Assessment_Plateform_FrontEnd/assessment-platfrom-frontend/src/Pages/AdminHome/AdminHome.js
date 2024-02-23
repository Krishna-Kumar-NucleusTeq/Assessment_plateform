import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './AdminHome.css';
import UnauthorizedAccess from '../../Components/UnauthrizedAccess/UnauthorizedAccess';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import Swal from 'sweetalert2';
import { LoadUsers } from '../../ApiService/ApiService';
import Sidebar from '../../Components/SideBar/Sidebar';
import axios from 'axios';

const AdminHome = () => {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadUsers();
  }, []);


  // const loadUsers = async () => {
  //   try {
  //     const result = await LoadUsers();
  //     setUsers(result);
  //   } catch (error) {
  //     if (error.response) {
  //       Swal.fire('Error', error.response.data.message, 'error');
  //     }
  //   }
  // };

  const loadUsers = async () => {
    try {
      const token = localStorage.getItem('token');
      if (!token) {
        console.error('Token not available');
        return;
      }
      const response = await axios.get('http://localhost:9098/users/get/all', {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        withCredentials: true,
      });

      setUsers(response.data);
    } catch (error) {
      if (error.response) {
        Swal.fire('Error', error.response.data.message, 'error');
        console.error('Error:', error.message);
      }
    }
  // if (userRole !== 'admin') {
  //   return (
  //     <UnauthorizedAccess />
  //   );
  // }

  return (
    <div className="admin-home-wrapper">
      <div className='admin-main-container'>
        <div className='siderbar-column'>
          <Sidebar />
        </div>
        <div className='admin-column'>
          <div className="admin-home-container">
            <div className="admin-home-card">
              <div className="admin-home-card-header">
                <h3>Welcome To Admin Dashboard</h3>
              </div>
              <div className="admin-home-card-body">
                <div className="admin-home-table-wrapper">
                  <table className="admin-home-table">
                    <thead className="admin-home-table-secondary">
                      <tr>
                        <th scope="col">S.No.</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Mobile Number</th>
                        <th scope="col">User Role</th>
                        <th scope="col">Email</th>
                      </tr>
                    </thead>
                    <tbody>
                      {users.map((user, index) => (
                        <tr key={index}>
                          <td>{index + 1}</td>
                          <td>{user.firstName + ' ' + user.lastName}</td>
                          <td>{user.mobileNumber}</td>
                          <td>{user.userRole}</td>
                          <td>{user.email}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminHome;