package com.ices.ethereumevent.config;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class VotesContract extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200296c3803806200296c833981810160405281019062000037919062000199565b62000048816200004f60201b60201c565b506200036e565b8060029080519060200190620000679291906200006b565b5050565b82805462000079906200027f565b90600052602060002090601f0160209004810192826200009d5760008555620000e9565b82601f10620000b857805160ff1916838001178555620000e9565b82800160010185558215620000e9579182015b82811115620000e8578251825591602001919060010190620000cb565b5b509050620000f89190620000fc565b5090565b5b8082111562000117576000816000905550600101620000fd565b5090565b6000620001326200012c8462000213565b620001ea565b9050828152602081018484840111156200015157620001506200034e565b5b6200015e84828562000249565b509392505050565b600082601f8301126200017e576200017d62000349565b5b8151620001908482602086016200011b565b91505092915050565b600060208284031215620001b257620001b162000358565b5b600082015167ffffffffffffffff811115620001d357620001d262000353565b5b620001e18482850162000166565b91505092915050565b6000620001f662000209565b9050620002048282620002b5565b919050565b6000604051905090565b600067ffffffffffffffff8211156200023157620002306200031a565b5b6200023c826200035d565b9050602081019050919050565b60005b83811015620002695780820151818401526020810190506200024c565b8381111562000279576000848401525b50505050565b600060028204905060018216806200029857607f821691505b60208210811415620002af57620002ae620002eb565b5b50919050565b620002c0826200035d565b810181811067ffffffffffffffff82111715620002e257620002e16200031a565b5b80604052505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b6125ee806200037e6000396000f3fe608060405234801561001057600080fd5b50600436106100875760003560e01c80634e1273f41161005b5780634e1273f414610138578063a22cb46514610168578063e985e9c514610184578063f242432a146101b457610087565b8062fdd58e1461008c57806301ffc9a7146100bc5780630e89341c146100ec5780632eb2c2d61461011c575b600080fd5b6100a660048036038101906100a19190611727565b6101d0565b6040516100b39190611d80565b60405180910390f35b6100d660048036038101906100d191906117df565b610299565b6040516100e39190611c03565b60405180910390f35b61010660048036038101906101019190611839565b61037b565b6040516101139190611c1e565b60405180910390f35b61013660048036038101906101319190611581565b61040f565b005b610152600480360381019061014d9190611767565b6104b0565b60405161015f9190611baa565b60405180910390f35b610182600480360381019061017d91906116e7565b6105c9565b005b61019e60048036038101906101999190611541565b6105df565b6040516101ab9190611c03565b60405180910390f35b6101ce60048036038101906101c99190611650565b610673565b005b60008073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610241576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161023890611c80565b60405180910390fd5b60008083815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b60007fd9b67a26000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916148061036457507f0e89341c000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916145b80610374575061037382610714565b5b9050919050565b60606002805461038a90611fef565b80601f01602080910402602001604051908101604052809291908181526020018280546103b690611fef565b80156104035780601f106103d857610100808354040283529160200191610403565b820191906000526020600020905b8154815290600101906020018083116103e657829003601f168201915b50505050509050919050565b61041761077e565b73ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff16148061045d575061045c8561045761077e565b6105df565b5b61049c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161049390611ce0565b60405180910390fd5b6104a98585858585610786565b5050505050565b606081518351146104f6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104ed90611d40565b60405180910390fd5b6000835167ffffffffffffffff81111561051357610512612128565b5b6040519080825280602002602001820160405280156105415781602001602082028036833780820191505090505b50905060005b84518110156105be5761058e858281518110610566576105656120f9565b5b6020026020010151858381518110610581576105806120f9565b5b60200260200101516101d0565b8282815181106105a1576105a06120f9565b5b602002602001018181525050806105b790612052565b9050610547565b508091505092915050565b6105db6105d461077e565b8383610aa8565b5050565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b61067b61077e565b73ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff1614806106c157506106c0856106bb61077e565b6105df565b5b610700576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106f790611ca0565b60405180910390fd5b61070d8585858585610c15565b5050505050565b60007f01ffc9a7000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916827bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916149050919050565b600033905090565b81518351146107ca576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107c190611d60565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff16141561083a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161083190611cc0565b60405180910390fd5b600061084461077e565b9050610854818787878787610eb1565b60005b8451811015610a05576000858281518110610875576108746120f9565b5b602002602001015190506000858381518110610894576108936120f9565b5b60200260200101519050600080600084815260200190815260200160002060008b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905081811015610935576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161092c90611d00565b60405180910390fd5b81810360008085815260200190815260200160002060008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508160008085815260200190815260200160002060008b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546109ea9190611ee3565b92505081905550505050806109fe90612052565b9050610857565b508473ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff167f4a39dc06d4c0dbc64b70af90fd698a233a518aa5d07e595d983b8c0526c8f7fb8787604051610a7c929190611bcc565b60405180910390a4610a92818787878787610eb9565b610aa0818787878787610ec1565b505050505050565b8173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415610b17576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b0e90611d20565b60405180910390fd5b80600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c3183604051610c089190611c03565b60405180910390a3505050565b600073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff161415610c85576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c7c90611cc0565b60405180910390fd5b6000610c8f61077e565b90506000610c9c856110a8565b90506000610ca9856110a8565b9050610cb9838989858589610eb1565b600080600088815260200190815260200160002060008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905085811015610d50576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d4790611d00565b60405180910390fd5b85810360008089815260200190815260200160002060008b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508560008089815260200190815260200160002060008a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610e059190611ee3565b925050819055508773ffffffffffffffffffffffffffffffffffffffff168973ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff167fc3d58168c5ae7397731d063d5bbf3d657854427343f4c083240f7aacaa2d0f628a8a604051610e82929190611d9b565b60405180910390a4610e98848a8a86868a610eb9565b610ea6848a8a8a8a8a611122565b505050505050505050565b505050505050565b505050505050565b610ee08473ffffffffffffffffffffffffffffffffffffffff16611309565b156110a0578373ffffffffffffffffffffffffffffffffffffffff1663bc197c8187878686866040518663ffffffff1660e01b8152600401610f26959493929190611ae8565b602060405180830381600087803b158015610f4057600080fd5b505af1925050508015610f7157506040513d601f19601f82011682018060405250810190610f6e919061180c565b60015b61101757610f7d612157565b806308c379a01415610fda5750610f926124c6565b80610f9d5750610fdc565b806040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610fd19190611c1e565b60405180910390fd5b505b6040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161100e90611c40565b60405180910390fd5b63bc197c8160e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916817bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161461109e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161109590611c60565b60405180910390fd5b505b505050505050565b60606000600167ffffffffffffffff8111156110c7576110c6612128565b5b6040519080825280602002602001820160405280156110f55781602001602082028036833780820191505090505b509050828160008151811061110d5761110c6120f9565b5b60200260200101818152505080915050919050565b6111418473ffffffffffffffffffffffffffffffffffffffff16611309565b15611301578373ffffffffffffffffffffffffffffffffffffffff1663f23a6e6187878686866040518663ffffffff1660e01b8152600401611187959493929190611b50565b602060405180830381600087803b1580156111a157600080fd5b505af19250505080156111d257506040513d601f19601f820116820180604052508101906111cf919061180c565b60015b611278576111de612157565b806308c379a0141561123b57506111f36124c6565b806111fe575061123d565b806040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016112329190611c1e565b60405180910390fd5b505b6040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161126f90611c40565b60405180910390fd5b63f23a6e6160e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916817bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916146112ff576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016112f690611c60565b60405180910390fd5b505b505050505050565b6000808273ffffffffffffffffffffffffffffffffffffffff163b119050919050565b600061133f61133a84611de9565b611dc4565b905080838252602082019050828560208602820111156113625761136161217e565b5b60005b858110156113925781611378888261144e565b845260208401935060208301925050600181019050611365565b5050509392505050565b60006113af6113aa84611e15565b611dc4565b905080838252602082019050828560208602820111156113d2576113d161217e565b5b60005b8581101561140257816113e8888261152c565b8452602084019350602083019250506001810190506113d5565b5050509392505050565b600061141f61141a84611e41565b611dc4565b90508281526020810184848401111561143b5761143a612183565b5b611446848285611fad565b509392505050565b60008135905061145d8161255c565b92915050565b600082601f83011261147857611477612179565b5b813561148884826020860161132c565b91505092915050565b600082601f8301126114a6576114a5612179565b5b81356114b684826020860161139c565b91505092915050565b6000813590506114ce81612573565b92915050565b6000813590506114e38161258a565b92915050565b6000815190506114f88161258a565b92915050565b600082601f83011261151357611512612179565b5b813561152384826020860161140c565b91505092915050565b60008135905061153b816125a1565b92915050565b600080604083850312156115585761155761218d565b5b60006115668582860161144e565b92505060206115778582860161144e565b9150509250929050565b600080600080600060a0868803121561159d5761159c61218d565b5b60006115ab8882890161144e565b95505060206115bc8882890161144e565b945050604086013567ffffffffffffffff8111156115dd576115dc612188565b5b6115e988828901611491565b935050606086013567ffffffffffffffff81111561160a57611609612188565b5b61161688828901611491565b925050608086013567ffffffffffffffff81111561163757611636612188565b5b611643888289016114fe565b9150509295509295909350565b600080600080600060a0868803121561166c5761166b61218d565b5b600061167a8882890161144e565b955050602061168b8882890161144e565b945050604061169c8882890161152c565b93505060606116ad8882890161152c565b925050608086013567ffffffffffffffff8111156116ce576116cd612188565b5b6116da888289016114fe565b9150509295509295909350565b600080604083850312156116fe576116fd61218d565b5b600061170c8582860161144e565b925050602061171d858286016114bf565b9150509250929050565b6000806040838503121561173e5761173d61218d565b5b600061174c8582860161144e565b925050602061175d8582860161152c565b9150509250929050565b6000806040838503121561177e5761177d61218d565b5b600083013567ffffffffffffffff81111561179c5761179b612188565b5b6117a885828601611463565b925050602083013567ffffffffffffffff8111156117c9576117c8612188565b5b6117d585828601611491565b9150509250929050565b6000602082840312156117f5576117f461218d565b5b6000611803848285016114d4565b91505092915050565b6000602082840312156118225761182161218d565b5b6000611830848285016114e9565b91505092915050565b60006020828403121561184f5761184e61218d565b5b600061185d8482850161152c565b91505092915050565b60006118728383611aca565b60208301905092915050565b61188781611f39565b82525050565b600061189882611e82565b6118a28185611eb0565b93506118ad83611e72565b8060005b838110156118de5781516118c58882611866565b97506118d083611ea3565b9250506001810190506118b1565b5085935050505092915050565b6118f481611f4b565b82525050565b600061190582611e8d565b61190f8185611ec1565b935061191f818560208601611fbc565b61192881612192565b840191505092915050565b600061193e82611e98565b6119488185611ed2565b9350611958818560208601611fbc565b61196181612192565b840191505092915050565b6000611979603483611ed2565b9150611984826121b0565b604082019050919050565b600061199c602883611ed2565b91506119a7826121ff565b604082019050919050565b60006119bf602b83611ed2565b91506119ca8261224e565b604082019050919050565b60006119e2602983611ed2565b91506119ed8261229d565b604082019050919050565b6000611a05602583611ed2565b9150611a10826122ec565b604082019050919050565b6000611a28603283611ed2565b9150611a338261233b565b604082019050919050565b6000611a4b602a83611ed2565b9150611a568261238a565b604082019050919050565b6000611a6e602983611ed2565b9150611a79826123d9565b604082019050919050565b6000611a91602983611ed2565b9150611a9c82612428565b604082019050919050565b6000611ab4602883611ed2565b9150611abf82612477565b604082019050919050565b611ad381611fa3565b82525050565b611ae281611fa3565b82525050565b600060a082019050611afd600083018861187e565b611b0a602083018761187e565b8181036040830152611b1c818661188d565b90508181036060830152611b30818561188d565b90508181036080830152611b4481846118fa565b90509695505050505050565b600060a082019050611b65600083018861187e565b611b72602083018761187e565b611b7f6040830186611ad9565b611b8c6060830185611ad9565b8181036080830152611b9e81846118fa565b90509695505050505050565b60006020820190508181036000830152611bc4818461188d565b905092915050565b60006040820190508181036000830152611be6818561188d565b90508181036020830152611bfa818461188d565b90509392505050565b6000602082019050611c1860008301846118eb565b92915050565b60006020820190508181036000830152611c388184611933565b905092915050565b60006020820190508181036000830152611c598161196c565b9050919050565b60006020820190508181036000830152611c798161198f565b9050919050565b60006020820190508181036000830152611c99816119b2565b9050919050565b60006020820190508181036000830152611cb9816119d5565b9050919050565b60006020820190508181036000830152611cd9816119f8565b9050919050565b60006020820190508181036000830152611cf981611a1b565b9050919050565b60006020820190508181036000830152611d1981611a3e565b9050919050565b60006020820190508181036000830152611d3981611a61565b9050919050565b60006020820190508181036000830152611d5981611a84565b9050919050565b60006020820190508181036000830152611d7981611aa7565b9050919050565b6000602082019050611d956000830184611ad9565b92915050565b6000604082019050611db06000830185611ad9565b611dbd6020830184611ad9565b9392505050565b6000611dce611ddf565b9050611dda8282612021565b919050565b6000604051905090565b600067ffffffffffffffff821115611e0457611e03612128565b5b602082029050602081019050919050565b600067ffffffffffffffff821115611e3057611e2f612128565b5b602082029050602081019050919050565b600067ffffffffffffffff821115611e5c57611e5b612128565b5b611e6582612192565b9050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b6000611eee82611fa3565b9150611ef983611fa3565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115611f2e57611f2d61209b565b5b828201905092915050565b6000611f4482611f83565b9050919050565b60008115159050919050565b60007fffffffff0000000000000000000000000000000000000000000000000000000082169050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b82818337600083830152505050565b60005b83811015611fda578082015181840152602081019050611fbf565b83811115611fe9576000848401525b50505050565b6000600282049050600182168061200757607f821691505b6020821081141561201b5761201a6120ca565b5b50919050565b61202a82612192565b810181811067ffffffffffffffff8211171561204957612048612128565b5b80604052505050565b600061205d82611fa3565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8214156120905761208f61209b565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600060033d11156121765760046000803e6121736000516121a3565b90505b90565b600080fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b60008160e01c9050919050565b7f455243313135353a207472616e7366657220746f206e6f6e204552433131353560008201527f526563656976657220696d706c656d656e746572000000000000000000000000602082015250565b7f455243313135353a204552433131353552656365697665722072656a6563746560008201527f6420746f6b656e73000000000000000000000000000000000000000000000000602082015250565b7f455243313135353a2062616c616e636520717565727920666f7220746865207a60008201527f65726f2061646472657373000000000000000000000000000000000000000000602082015250565b7f455243313135353a2063616c6c6572206973206e6f74206f776e6572206e6f7260008201527f20617070726f7665640000000000000000000000000000000000000000000000602082015250565b7f455243313135353a207472616e7366657220746f20746865207a65726f20616460008201527f6472657373000000000000000000000000000000000000000000000000000000602082015250565b7f455243313135353a207472616e736665722063616c6c6572206973206e6f742060008201527f6f776e6572206e6f7220617070726f7665640000000000000000000000000000602082015250565b7f455243313135353a20696e73756666696369656e742062616c616e636520666f60008201527f72207472616e7366657200000000000000000000000000000000000000000000602082015250565b7f455243313135353a2073657474696e6720617070726f76616c2073746174757360008201527f20666f722073656c660000000000000000000000000000000000000000000000602082015250565b7f455243313135353a206163636f756e747320616e6420696473206c656e67746860008201527f206d69736d617463680000000000000000000000000000000000000000000000602082015250565b7f455243313135353a2069647320616e6420616d6f756e7473206c656e6774682060008201527f6d69736d61746368000000000000000000000000000000000000000000000000602082015250565b600060443d10156124d657612559565b6124de611ddf565b60043d036004823e80513d602482011167ffffffffffffffff82111715612506575050612559565b808201805167ffffffffffffffff8111156125245750505050612559565b80602083010160043d038501811115612541575050505050612559565b61255082602001850186612021565b82955050505050505b90565b61256581611f39565b811461257057600080fd5b50565b61257c81611f4b565b811461258757600080fd5b50565b61259381611f57565b811461259e57600080fd5b50565b6125aa81611fa3565b81146125b557600080fd5b5056fea264697066735822122093e3f9f09044b06d1ff1ed3f7860f036a0cc753929366a5ec61f3d5d8bcb340464736f6c63430008070033";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BALANCEOFBATCH = "balanceOfBatch";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_SAFEBATCHTRANSFERFROM = "safeBatchTransferFrom";

    public static final String FUNC_SAFETRANSFERFROM = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_URI = "uri";

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event TRANSFERBATCH_EVENT = new Event("TransferBatch", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event TRANSFERSINGLE_EVENT = new Event("TransferSingle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event URI_EVENT = new Event("URI", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected VotesContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VotesContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VotesContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VotesContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<TransferBatchEventResponse> getTransferBatchEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERBATCH_EVENT, transactionReceipt);
        ArrayList<TransferBatchEventResponse> responses = new ArrayList<TransferBatchEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferBatchEventResponse typedResponse = new TransferBatchEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.ids = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.values = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferBatchEventResponse> transferBatchEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferBatchEventResponse>() {
            @Override
            public TransferBatchEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERBATCH_EVENT, log);
                TransferBatchEventResponse typedResponse = new TransferBatchEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.ids = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.values = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferBatchEventResponse> transferBatchEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERBATCH_EVENT));
        return transferBatchEventFlowable(filter);
    }

    public List<TransferSingleEventResponse> getTransferSingleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERSINGLE_EVENT, transactionReceipt);
        ArrayList<TransferSingleEventResponse> responses = new ArrayList<TransferSingleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferSingleEventResponse typedResponse = new TransferSingleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferSingleEventResponse> transferSingleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferSingleEventResponse>() {
            @Override
            public TransferSingleEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERSINGLE_EVENT, log);
                TransferSingleEventResponse typedResponse = new TransferSingleEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferSingleEventResponse> transferSingleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERSINGLE_EVENT));
        return transferSingleEventFlowable(filter);
    }

    public List<URIEventResponse> getURIEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(URI_EVENT, transactionReceipt);
        ArrayList<URIEventResponse> responses = new ArrayList<URIEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            URIEventResponse typedResponse = new URIEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<URIEventResponse> uRIEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, URIEventResponse>() {
            @Override
            public URIEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(URI_EVENT, log);
                URIEventResponse typedResponse = new URIEventResponse();
                typedResponse.log = log;
                typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.value = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<URIEventResponse> uRIEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(URI_EVENT));
        return uRIEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account, BigInteger id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> balanceOfBatch(List<String> accounts, List<BigInteger> ids) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOFBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(accounts, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(ids, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String account, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeBatchTransferFrom(String from, String to, List<BigInteger> ids, List<BigInteger> amounts, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAFEBATCHTRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(ids, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(amounts, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger id, BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAFETRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> uri(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_URI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static VotesContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VotesContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VotesContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VotesContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VotesContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VotesContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VotesContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VotesContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VotesContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(VotesContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<VotesContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(VotesContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<VotesContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(VotesContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<VotesContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(VotesContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String account;

        public String operator;

        public Boolean approved;
    }

    public static class TransferBatchEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public List<BigInteger> ids;

        public List<BigInteger> values;
    }

    
    public static class TransferSingleEventResponse extends BaseEventResponse{

		public String operator;

        public String from;

        public String to;

        public BigInteger id;

        public BigInteger value;
    }

    public static class URIEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String value;
    }
}
