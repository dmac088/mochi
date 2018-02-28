import React, {Component} from 'react';
import {StyleSheet, View, TextInput, TouchableOpacity, Text } from 'react-native';

export default class LoginFor extends Component {
  render () {
    return (
      <View sytle={styles.container}>
        <TextInput
          placeholder="username or email"
          placeholderTextColor='rgba(255,255,255,0.7)'
          returnKeyType='next'
          onSubmitEditing={() => this.passwordInput.focus()}
          keyboardType="email-address"
          autocapitalize="none"
          autoCorrect=(false)
          style={styles.input}
          />
        <TextInput
          placeholder="password"
          placeholderTextColor='rgba(255,255,255,0.7)'
          returnKeyType='go'
          secureTextEntry
          style={styles.input}
          ref={(input) => this.passwordInput = input}
          />
          <TouchableOpacity style={styles.buttonContainer}>
            <Text style={styles.buttonText}>
              LOGIN
            </Text>
          </TouchableOpacity>
      </View>

    );
  }
}


const styles = StyleSheet.create({
  container: {
    padding: 20
  },
  input: {
    height: 40,
    backgroundColor: 'rgba(255,255,255,0.2)',
    marginBottom: 15,
    color: '#FFF',
    paddingHorizontal: 10
  },
  buttonContainer: {
    backgroundColor: '#2980b9',
    paddingVertical: 15
  },
  buttonText: {
    textAlign: 'center',
    color: '#FFFFFF',
    fontWeight: '700'
  }
});
