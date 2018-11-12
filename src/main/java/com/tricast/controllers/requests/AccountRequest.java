package com.tricast.controllers.requests;

import java.io.Serializable;

import com.tricast.repositories.entities.AccountType;

public class AccountRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5985720956758102034L;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Integer dob;
    private String address;
    private String phoneNumber;
    private String email;
    private String pin;
    private String bankAccountNumber;
    private String bankCardNumber;
    private AccountType accountType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Integer getDob() {
        return dob;
    }
    public void setDob(Integer dob) {
        this.dob = dob;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
    public String getBankCardNumber() {
        return bankCardNumber;
    }
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((bankAccountNumber == null) ? 0 : bankAccountNumber.hashCode());
        result = prime * result + ((bankCardNumber == null) ? 0 : bankCardNumber.hashCode());
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((pin == null) ? 0 : pin.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccountRequest other = (AccountRequest) obj;
        if (accountType != other.accountType) {
            return false;
        }
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (bankAccountNumber == null) {
            if (other.bankAccountNumber != null) {
                return false;
            }
        } else if (!bankAccountNumber.equals(other.bankAccountNumber)) {
            return false;
        }
        if (bankCardNumber == null) {
            if (other.bankCardNumber != null) {
                return false;
            }
        } else if (!bankCardNumber.equals(other.bankCardNumber)) {
            return false;
        }
        if (dob == null) {
            if (other.dob != null) {
                return false;
            }
        } else if (!dob.equals(other.dob)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (phoneNumber == null) {
            if (other.phoneNumber != null) {
                return false;
            }
        } else if (!phoneNumber.equals(other.phoneNumber)) {
            return false;
        }
        if (pin == null) {
            if (other.pin != null) {
                return false;
            }
        } else if (!pin.equals(other.pin)) {
            return false;
        }
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AccountRequest [userName=" + userName + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", dob=" + dob + ", address=" + address + ", phoneNumber=" + phoneNumber
                + ", email=" + email + ", pin=" + pin + ", bankaccountnumber=" + bankAccountNumber + ", bankcardnumber="
                + bankCardNumber + ", accounttype=" + accountType + "]";
    }

}
