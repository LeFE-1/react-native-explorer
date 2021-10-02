import React, { Component } from 'react';
import { StyleSheet, View, Text, ScrollView } from 'react-native';

export default class App extends Component {
  render() {
    return (
      <ScrollView>
        <View style={{ display: 'flex', flexDirection: 'row', paddingHorizontal: 10 }}>
          <View style={{ flex: 1, borderWidth: 1, height: 100 }}>
            <Text>item 1</Text>
          </View>
          <View style={{ flex: 1, borderWidth: 1, height: 100 }}>
            <Text>item 2</Text>
          </View>
          <View style={{ flex: 1, borderWidth: 1, height: 100 }}>
            <Text>item 3</Text>
          </View>
        </View>
        {/* justifyContent */}
        <View style={[styles.flexWrap, { justifyContent: 'flex-start' }]}>
          <View style={styles.item}><Text>flex-start 1</Text></View>
          <View style={styles.item}><Text>flex-start 2</Text></View>
          <View style={styles.item}><Text>flex-start 3</Text></View>
        </View>
        <View style={[styles.flexWrap, { justifyContent: 'flex-end' }]}>
          <View style={styles.item}><Text>flex-end 1</Text></View>
          <View style={styles.item}><Text>flex-end 2</Text></View>
          <View style={styles.item}><Text>flex-end 3</Text></View>
        </View>
        <View style={[styles.flexWrap, { justifyContent: 'center' }]}>
          <View style={styles.item}><Text>center 1</Text></View>
          <View style={styles.item}><Text>center 2</Text></View>
          <View style={styles.item}><Text>center 3</Text></View>
        </View>
        <View style={[styles.flexWrap, { justifyContent: 'space-between' }]}>
          <View style={styles.item}><Text>space-between 1</Text></View>
          <View style={styles.item}><Text>space-between 2</Text></View>
          <View style={styles.item}><Text>space-between 3</Text></View>
        </View>
        <View style={[styles.flexWrap, { justifyContent: 'space-around' }]}>
          <View style={styles.item}><Text>space-around 1</Text></View>
          <View style={styles.item}><Text>space-around 2</Text></View>
          <View style={styles.item}><Text>space-around 3</Text></View>
        </View>
        {/* alignItems */}
        <View style={[styles.flexCrossWrap, { alignItems: 'flex-start' }]}>
          <View style={styles.item}><Text>flex-start 1</Text></View>
          <View style={styles.item}><Text>flex-start 2</Text></View>
          <View style={styles.item}><Text>flex-start 3</Text></View>
        </View>
        <View style={[styles.flexCrossWrap, { alignItems: 'flex-end' }]}>
          <View style={styles.item}><Text>flex-end 1</Text></View>
          <View style={styles.item}><Text>flex-end 2</Text></View>
          <View style={styles.item}><Text>flex-end 3</Text></View>
        </View>
        <View style={[styles.flexCrossWrap, { alignItems: 'center' }]}>
          <View style={styles.item}><Text>center 1</Text></View>
          <View style={styles.item}><Text>center 2</Text></View>
          <View style={styles.item}><Text>center 3</Text></View>
        </View>
        <View style={[styles.flexCrossWrap, { alignItems: 'baseline' }]}>
          <View style={styles.item}><Text style={{ fontSize: 40 }}>40</Text></View>
          <View style={styles.item}><Text style={{ fontSize: 30 }}>30</Text></View>
          <View style={styles.item}><Text style={{ fontSize: 20 }}>20</Text></View>
        </View>
        <View style={[styles.flexCrossWrap]}>
          <View style={styles.item}><Text style={{ fontSize: 40 }}>40</Text></View>
          <View style={styles.item}><Text style={{ fontSize: 30 }}>30</Text></View>
          <View style={styles.item}><Text style={{ fontSize: 20 }}>20</Text></View>
        </View>
        <View style={[styles.flexCrossWrap, { alignItems: 'stretch' }]}>
          <View style={styles.item}><Text>stretch 1</Text></View>
          <View style={styles.item}><Text>stretch 2</Text></View>
          <View style={styles.item}><Text>stretch 3</Text></View>
        </View>
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  flexWrap: {
    display: 'flex',
    flexDirection: 'row',
    paddingVertical: 10,
  },
  flexCrossWrap: {
    display: 'flex',
    flexDirection: 'row',
    margin: 10,
    height: 50,
    borderWidth: 1,
    flexWrap: 'wrap'
  },
  item: {
    width: 100,
    borderWidth: 1,
    // height: 30,
  }
});
